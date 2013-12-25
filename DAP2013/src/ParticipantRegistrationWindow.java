//Window where user can choose participants to register for a specific workshop

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ParticipantRegistrationWindow extends JDialog implements ActionListener{
	private final Workshop workshop;
	private final JTable table;
	private Object[]data;
	private ArrayList<Object>participants;
	private DefaultTableModel model;
	private final JScrollPane scrollPane;
	private final JButton saveButton;

	public ParticipantRegistrationWindow(Workshop w){
		super.setTitle(w.getTitle());
		this.setLayout(new FlowLayout());
		workshop = w;
		table = new MyTableModel();
		table.getTableHeader().setReorderingAllowed(false);
		saveButton = new JButton("Register Selected Participants");
		saveButton.addActionListener(this);
		saveButton.setActionCommand("save");
		this.setSize(1000,450);
		loadData();
		createTable();
		scrollPane = new JScrollPane(table);
	    this.add(saveButton);
	    this.add(scrollPane);
	}
	public void loadData(){
		participants = ReadFromFile.getData("PARTICIPANTS");
	}
	public void createTable(){
		String [] columnNames = {"Type","First","Last","Chapter","Register"};
        model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnNames);
		data = new Object[5];
		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			data[0]= p.getType().toString();
			data[1]= p.getFirstName().toString();
			data[2]= p.getLastName().toString();
			data[3]= p.getChapter().toString();
			data[4] = new Boolean(false);
			model.addRow(data);
		}
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
			for(int j = 0; j < table.getColumnCount();j++){
				if(model.getValueAt(i, j).equals(Boolean.TRUE)){
					//write into the middle of WKSHOP_REGISTRATIONS.txt
				}
			}
		}
	}
}
