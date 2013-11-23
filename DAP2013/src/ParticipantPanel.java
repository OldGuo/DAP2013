import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ParticipantPanel extends JPanel{
	private JTable table;
	private Object[]data;
	private ArrayList<Participant>participants;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	public ParticipantPanel(){
		loadData();
		createTable();
		scrollPane = new JScrollPane(table);
	    scrollPane.setPreferredSize(new Dimension(900,450));
	    this.add(scrollPane);
	}
	public void loadData(){
		participants = ReadFromFile.getParticipants();
	}
	public void createTable(){
		String [] columnNames = {"Type","First","Last","Chapter"};
		table = new MyTableModel();
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);
		data = new String[4];
		for(int i = 0; i < participants.size();i++){
			data[0]= participants.get(i).getType().toString();
			data[1]= participants.get(i).getFirstName().toString();
			data[2]= participants.get(i).getLastName().toString();
			data[3]= participants.get(i).getChapter().toString();
			model.addRow(data);
		}
	}
}
