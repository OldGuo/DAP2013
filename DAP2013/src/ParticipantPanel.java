import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
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
	private final JCheckBox DC, LA, MN;

	// Sort by:
	// Participant type, last name
	// chapter number, participant type, last name (generate a chapter registration confirmation)
	// each chapter should begin on a new page
	//
	//
	public ParticipantPanel(){
		DC = new JCheckBox("Show DC Participants");
		DC.setSelected(true);

		LA = new JCheckBox("Show LA Participants");
		LA.setSelected(true);

		MN = new JCheckBox("Show MN Participants");
		MN.setSelected(true);

		table = new MyTableModel();
		label = new JLabel("Participant list!!11");
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,450));

		createTable();

		this.add(label);
		this.add(scrollPane);
		this.add(DC);
		this.add(LA);
		this.add(MN);
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
	public void itemStateChanged(ItemEvent e) {
	    Object source = e.getItemSelectable();

	    if (source == DC) {
	        //update table
	    } else if (source == MN) {
	        //update table
	    } else if (source == LA) {
	        //update table
	    }
	}
}
