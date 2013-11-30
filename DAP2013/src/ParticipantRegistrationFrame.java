import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ParticipantRegistrationFrame extends JDialog{
	private JTable table;
	private Object[]data;
	private ArrayList<Participant>participants;
	private DefaultTableModel model;
	private final JScrollPane scrollPane;
	private final JButton saveButton;

	public ParticipantRegistrationFrame(){
		this.setLayout(new FlowLayout());
		saveButton = new JButton("Save Changes");
		this.setSize(900,450);
		loadData();
		createTable();
		scrollPane = new JScrollPane(table);
	    //scrollPane.setPreferredSize(new Dimension(800,450));
	    this.add(saveButton);
	    this.add(scrollPane);
	}
	public void loadData(){
		participants = ReadFromFile.getParticipants();
	}
	public void createTable(){
		String [] columnNames = {"Type","First","Last","Chapter","Register"};
        model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnNames);
		data = new Object[5];
		for(int i = 0; i < participants.size();i++){
			data[0]= participants.get(i).getType().toString();
			data[1]= participants.get(i).getFirstName().toString();
			data[2]= participants.get(i).getLastName().toString();
			data[3]= participants.get(i).getChapter().toString();
			model.addRow(data);
		}
	}
}
