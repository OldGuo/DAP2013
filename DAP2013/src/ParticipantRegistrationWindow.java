//Window where user can choose participants to register for a specific workshop

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ParticipantRegistrationWindow extends JDialog implements ActionListener{
	private final Workshop workshop;
	private ArrayList<Object>participants;
	private JScrollPane scrollPane;
	private final JButton saveButton;
	private MyTableModel model;
	private TableRowSorter<MyTableModel> sorter;
	private JTable table;

	public ParticipantRegistrationWindow(Workshop w){
		super.setTitle(w.getTitle());
		this.setLayout(new FlowLayout());
		workshop = w;
		saveButton = new JButton("Register Selected Participants");
		saveButton.addActionListener(this);
		saveButton.setActionCommand("save");
		this.setSize(1000,450);
		model = new MyTableModel();
		loadData();
		createTable();
	    this.add(saveButton);
	}
	public void loadData(){
		ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		participants = read.getData();
	}
	public void createTable(){
		String [] columnNames = {"Type","First","Last","Chapter","Register"};
        
        Object [][] data = new Object[participants.size()][5];
		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			for(int j = 0; j < 5; j++){
				data[i][0]= p.getType().toString();
				data[i][1]= p.getFirstName().toString();
				data[i][2]= p.getLastName().toString();
				data[i][3]= p.getChapter().toString();
				if(i%2 == 0)
					data[i][4] = new Boolean(true);
				else
					data[i][4] = new Boolean(false);
			}
		}
		model.setData(data);
		model.setColumns(columnNames);
        sorter = new TableRowSorter<MyTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
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
		for(int i = 0; i < table.getRowCount();i++){
			if(table.getValueAt(i, 4).equals(Boolean.TRUE)){
				//get participant based on the rest of their information
				String type = (String) table.getValueAt(i, 0);
				String first = (String) table.getValueAt(i, 1);
				String last = (String) table.getValueAt(i, 2);
				String chapter = (String) table.getValueAt(i, 3);
				
				Participant participant = ReadFromFile.getParticipant(type,first,last,chapter);
				PrintToFile print = new PrintToFile();
				print.registerForWorkshop(workshop,participant);
			}
		}
	}
}
