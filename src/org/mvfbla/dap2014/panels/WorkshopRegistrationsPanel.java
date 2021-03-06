package org.mvfbla.dap2014.panels;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.mvfbla.dap2014.base.Participant;
import org.mvfbla.dap2014.base.Workshop;
import org.mvfbla.dap2014.utilities.MyTableModel;
import org.mvfbla.dap2014.utilities.ReadFromFile;

public class WorkshopRegistrationsPanel extends JPanel{
	private Workshop workshop;
	private MyTableModel model;
	private Object[][]data;
	private String [] columnNames;
	private JTable table;
	private JScrollPane scrollPane;
	
	public WorkshopRegistrationsPanel(Workshop w){
		workshop = w;
		data = null;
		columnNames = null;
		model = new MyTableModel();
		table = new JTable();
		scrollPane = new JScrollPane();
		
		createTable();
	}
	/**
	 * @param o
	 * the object to create a table for
	 * @return
	 * the scroll pane containing the completed table
	 */
	public void createTable(){	
		//table list of participants registered from the workshops - from WKSHP_REGISTRATIONS
		int numParticipants = 0;
		
		ArrayList<String>registrations = ReadFromFile.getRegistrationList();
		columnNames = new String[4];
		columnNames[0] = "Type";
		columnNames[1] = "First";
		columnNames[2] = "Last";
		columnNames[3] = "Chapter";
		
		ArrayList<String>participants = new ArrayList<String>();
		
		for(int i = 0; i < registrations.size(); i++){ //lines of WKSHP_REGISTRATIONS
			String line = registrations.get(i);
			if(line.length() > 0){
				if(line.substring(1,4).equals(workshop.getID())){ //gets the registered ID's
					for(int j = 5; j < line.length(); j++){
						if(line.substring(j,j+1).equals("[")){
							String temp = "";
							int count = j+1;
							while(!line.substring(count,count+1).equals("]")){
								temp += line.substring(count,count+1);
								count++;
							}
							participants.add(temp);
							numParticipants++;
						}
					}
				}	
			}
		}
		data = new Object[numParticipants][4];
		for(int i = 0; i < participants.size();i++){
			Participant part = ReadFromFile.getParticipantByID(participants.get(i));
			data[i][0] = part.getType();
			data[i][1] = part.getFirstName();
			data[i][2] = part.getLastName();
			data[i][3] = part.getCode();
		}
		formatTable();
        this.add(scrollPane);
	}
	public void formatTable(){
		//get the data
		model.setData(data);
		model.setColumns(columnNames);
		table = new JTable(model);
		model = new MyTableModel(data,columnNames);
		        
		if(data.length > 0){
			table.setAutoCreateRowSorter(true);
		}
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,300));
        table.getColumnModel().getColumn(0).setPreferredWidth(90);		        	
	}
}
