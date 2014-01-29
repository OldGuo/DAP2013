//Window where user can choose participants to register for a specific workshop

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ParticipantRegistrationWindow extends JDialog implements ActionListener{
	private final Workshop workshop;
	private ArrayList<Object>participants;
	private JScrollPane scrollPane;
	private final JButton saveButton;
	private MyTableModel model;	
	private JFrame frame;
	private JTable table;

	public ParticipantRegistrationWindow(Workshop w){
		super.setTitle(w.getTitle());
		frame = this.frame;
		this.setLayout(new FlowLayout());
		workshop = w;
		saveButton = new JButton("Register Selected Participants");
		saveButton.addActionListener(this);
		saveButton.setActionCommand("save");
		this.setSize(1000,450);
		model = new MyTableModel();
		createTable();
	    this.add(saveButton);
	}
	public void createTable(){
		String [] columnNames = {"Type","First","Last","Chapter","Register"};
		participants = ReadFromFile.getData("PARTICIPANTS");
        //filter the participant list to only those registered to the specific conference
		for(int j = 0; j < participants.size(); j++){
			Participant p = (Participant)participants.get(j);
			if(!p.getCode().equals(workshop.getCode())){
				participants.remove(j);
				j--;
			}
		}
		Object [][] data = new Object[participants.size()][5];
		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			for(int j = 0; j < 5; j++){
				data[i][0]= p.getType().toString();
				data[i][1]= p.getFirstName().toString();
				data[i][2]= p.getLastName().toString();
				data[i][3]= p.getChapter().toString();
				data[i][4] = new Boolean(false);
			}
		}
		model.setData(data);
		model.setColumns(columnNames);

        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        
        scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,300));
        this.add(scrollPane);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	    if ("save".equals(e.getActionCommand())){
	    	Register();
	    	this.dispose();
	    }
	}
	public void Register(){
		boolean alreadyRegistered = false;
		for(int i = 0; i < table.getRowCount();i++){
			alreadyRegistered = false;
			if(table.getValueAt(i, 4).equals(Boolean.TRUE)){
				//get participant based on the rest of their information
				String type = (String) table.getValueAt(i, 0);
				String first = (String) table.getValueAt(i, 1);
				String last = (String) table.getValueAt(i, 2);
				String chapter = (String) table.getValueAt(i, 3);
				
				Participant participant = ReadFromFile.getParticipantByInfo(type,first,last,chapter);
				PrintToFile print = new PrintToFile();
				
				//check if already registered first
				ArrayList<String>registrations = ReadFromFile.getRegistrationList();
				for(int j = 0; j < registrations.size(); j++){
					String line = registrations.get(j);
					for(int k = 5; k < line.length(); k++){
						if(line.substring(k,k+1).equals("[")){
							String temp = "";
							int count = k+1;
							while(!line.substring(count,count+1).equals("]")){
								temp += line.substring(count,count+1);
								count++;
							}
							if(temp.equals(participant.getID()) && line.substring(1,4).equals(workshop.getID())){
								alreadyRegistered = true;
							}
						}
					}
				}
				if(alreadyRegistered == false){
					//do some error checking with the registration
					JOptionPane.showMessageDialog(frame, "Registration Successful: " + participant.getFirstName() + " registered");
					print.registerForWorkshop(workshop,participant);	
				}else{
					String dialogString = "Registration Unsuccessful: " + participant.getFirstName() + " already registered";
					JOptionPane.showMessageDialog(frame, dialogString,"Already Registered",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
