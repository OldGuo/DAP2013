package org.mvfbla.dap2014.panels;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.mvfbla.dap2014.base.Participant;
import org.mvfbla.dap2014.utilities.MyTableModel;
import org.mvfbla.dap2014.utilities.ReadFromFile;


/**
 * @author Young
 * Panel showing a list of participants specific to each conference
 */
public class ParticipantPanel extends JPanel implements ItemListener{

	private JScrollPane scrollPane;
	private JTable table;
	private ArrayList<Object>participants;
	private MyTableModel model;
	private Object [][] data;
	private String [] columnNames;
	private String conferenceCode;

	/**
	 * @param code
	 * Conference code to filter for
	 */
	public ParticipantPanel(String code){
		conferenceCode = code;
		
		model = new MyTableModel();  
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
	/**
	 * Creates the table after filtering participants for the specific conference
	 */
	public void createTable(){
		participants = ReadFromFile.getData("PARTICIPANTS");
		
		filterParticipants();
		
		data = new Object[participants.size()][5];
		String [] columnNames = {"Conference","Type","First","Last","Chapter"};

		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			data[i][0] = p.getCode().toString();
			data[i][1] = p.getType().toString();
			data[i][2] = p.getFirstName().toString();
			data[i][3] = p.getLastName().toString();
			data[i][4] = p.getChapter().toString();
		}
		model.setData(data);
		model.setColumns(columnNames);
		
        this.add(scrollPane);
		table.setModel(model);
	}
	/**
	 * Filters participants not in the specified conference code
	 */
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
