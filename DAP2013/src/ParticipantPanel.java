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
import javax.swing.table.TableRowSorter;


public class ParticipantPanel extends JPanel implements ItemListener{

	private JScrollPane scrollPane;
	private JTable table;
	private ArrayList<Object>participants;
	private final JCheckBox DC, LA, MN;
	private TableRowSorter<MyTableModel> sorter;
	private MyTableModel model;

	// Sort by:
	// Participant type, last name
	// chapter number, participant type, last name (generate a chapter registration confirmation)
	// each chapter should begin on a new page
	// http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableFilterDemoProject/src/components/TableFilterDemo.java
	//
	public ParticipantPanel(){
		setLayout(new FlowLayout());
		DC = new JCheckBox("Show DC Participants",true);
		DC.addItemListener(this);

		LA = new JCheckBox("Show LA Participants",true);
		LA.addItemListener(this);

		MN = new JCheckBox("Show MN Participants",true);
		MN.addItemListener(this);
		
		model = new MyTableModel();
	    table = new JTable(model);    
		//table = new JTable();
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,450));
	    table.setAutoCreateRowSorter(true);
	    table.getTableHeader().setReorderingAllowed(false);
	        
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
		
		Object [][] data = new Object[participants.size()][4];
		String [] columnNames = {"Type","First","Last","Chapter"};

		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			data[i][0] = p.getType().toString();
			data[i][1] = p.getFirstName().toString();
			data[i][2] = p.getLastName().toString();
			data[i][3] = p.getChapter().toString(); //change back to chapter later
		}
		model.setData(data);
		model.setColumns(columnNames);
		sorter = new TableRowSorter<MyTableModel>(model);
		table.setRowSorter(sorter);
		
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
		//createTable();
		//table.repaint();
	}
}
