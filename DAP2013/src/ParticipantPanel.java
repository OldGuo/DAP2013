import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ParticipantPanel extends JPanel implements ItemListener{

	private JScrollPane scrollPane;
	private JTable table;
	private ArrayList<Object>participants;
	private MyTableModel model;
	private Object [][] data;
	private String [] columnNames;
	private String conferenceCode;

	// Sort by:
	// Participant type, last name
	// chapter number, participant type, last name (generate a chapter registration confirmation)
	// each chapter should begin on a new page
	// http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableFilterDemoProject/src/components/TableFilterDemo.java
	//
	public ParticipantPanel(String code){
		conferenceCode = code;
		
		table = new JTable();
        table.getTableHeader().setResizingAllowed(false);
        table.setAutoCreateRowSorter(true);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,325));
		
		createTable();
		
		this.add(scrollPane);
	}
	public void createTable(){
		ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		participants = read.getData();

		filterParticipants();
		
		data = new Object[participants.size()][5];
		String [] columnNames = {"Conference","Type","First","Last","Chapter"};

		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			data[i][0] = p.getCode().toString();
			data[i][1] = p.getType().toString();
			data[i][2] = p.getFirstName().toString();
			data[i][3] = p.getLastName().toString();
			data[i][4] = p.getChapter().toString(); //change back to chapter later
		}
		table.setModel(new MyTableModel(data,columnNames));
	}
	public void filterParticipants(){
		for(int i = 0; i < participants.size(); i++){
			Participant p = (Participant)participants.get(i);
			if(p.getCode().equals(conferenceCode) == false){
				participants.remove(i);
				i--;
			}
		}
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
