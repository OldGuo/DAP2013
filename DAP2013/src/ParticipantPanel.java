import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ParticipantPanel extends JPanel{

	private final JScrollPane scrollPane;
	private final JTable table;
	private Object[]data;
	private final JLabel label;
	private DefaultTableModel model;
	private ArrayList<Object>participants;

	public ParticipantPanel(){
		table = new MyTableModel();
		label = new JLabel("Participant list!!11");
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,450));

		createTable();

		this.add(label);
		this.add(scrollPane);
	}
	public void createTable(){
		ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		participants = read.getData();

		String [] columnNames = {"Type","First","Last","Chapter"};
        model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnNames);
		data = new Object[4];
		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			data[0]= p.getType().toString();
			data[1]= p.getFirstName().toString();
			data[2]= p.getLastName().toString();
			data[3]= p.getChapter().toString();
			model.addRow(data);
		}
	}
}
