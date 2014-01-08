import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ParticipantPanel extends JPanel implements ItemListener{

	private final JScrollPane scrollPane;
	private final JTable table;
	private Object[]data;
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
		setLayout(new FlowLayout());
		DC = new JCheckBox("Show DC Participants");
		DC.addItemListener(this);

		LA = new JCheckBox("Show LA Participants");
		LA.addItemListener(this);

		MN = new JCheckBox("Show MN Participants");
		MN.addItemListener(this);

		table = new MyTableModel();

		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,450));

		DC.setSelected(true);
		LA.setSelected(true);
		MN.setSelected(true);

		this.add(DC);
		this.add(LA);
		this.add(MN);

		createTable();
	}
	public void createTable(){
		ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		participants = read.getData();

		//filter the t able based on the states of the checkboxes
		filterParticipants();

		String [] columnNames = {"Type","First","Last","Chapter"};
        model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnNames);
        model.setRowCount(0);

		data = new Object[4];
		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			data[0]= p.getType().toString();
			data[1]= p.getFirstName().toString();
			data[2]= p.getLastName().toString();
			data[3]= p.getCode().toString(); //change back to chapter later
			model.addRow(data);
		}
		this.add(scrollPane);
	}
	public void filterParticipants(){
		for(int i = 0; i < participants.size();i++)
		{
			Participant p = (Participant)participants.get(i);

			if(p.getCode().equals("DC") && !DC.isSelected()){
				participants.remove(i);
				i--;
			}
			if(p.getCode().equals("MN") && !MN.isSelected()){
				participants.remove(i);
				i--;
			}
			if(p.getCode().equals("LA") && !LA.isSelected()){
				participants.remove(i);
				i--;
			}
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		createTable();
		table.repaint();
	}
}
