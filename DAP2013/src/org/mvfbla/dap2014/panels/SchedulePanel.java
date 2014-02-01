package org.mvfbla.dap2014.panels;


import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.mvfbla.dap2014.base.Conference;
import org.mvfbla.dap2014.base.Participant;
import org.mvfbla.dap2014.base.Workshop;
import org.mvfbla.dap2014.utilities.MyTableModel;
import org.mvfbla.dap2014.utilities.ReadFromFile;


/**
 * @author Young
 * Creates schedules for each specific participant
 */
public class SchedulePanel extends JPanel{
	private Object [][] data;
	private String [] columnNames;
	private MyTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private Conference conference;
	private int day;
	
	/**
	 * @param p
	 * Participant to create a schedule for
	 * @param c
	 * Conference the participant is registered to
	 * @param d
	 * First or second day of the conference
	 */
	public SchedulePanel(Participant p, Conference c, int d){
		data = null;
		day = d;
		conference = c;
		columnNames = null;
		model = new MyTableModel();
		table = new JTable();
		scrollPane = new JScrollPane();
		columnNames = new String[2];
		columnNames[0] = "Time";
		columnNames[1] = "Event";
		data = new Object[25][2];
		createTable(day,p);
	}
	/**
	 * @param d
	 * First or second day of the conference
	 * @param p
	 * Participant to create a schedule for
	 */
	public void createTable(int d, Participant p){
		//WORKSHOP ARE 45 MINUTES LONG
		//Day 1: 1PM - 3PM (Workshops)
		//9,930,10,1030,11,1130,12,1230,1,130,2,230,3,330,4,430,5,530,6,630,7,730,8,830,9
		/* Registration 11 AM, 7 PM
		 * Opening General Session 9PM 	
		 * */
		//Day 2: 9AM - 3:30 PM
		//9,930,10,1030,11,1130,12,1230,1,130,2,230,3,330,4,430,5,530,6,630,7,730,8,830,9
		/* Closing General Session 5 PM
		 * Blue Jeans for babies dance 9 PM
		 * */
		int day = d;
		Participant participant = p;
		
		for(int i = 0; i < data.length; i++){
			data[i][0] = convertToDigital(i);
			data[i][1] = "-";
		}
		if(d == 1){
			data[4][1] = "Registration";
			data[20][1] = "Registration";
			data[24][1] = "Opening General Session";
		}else{
			data[16][1] = "Closing General Session";
			data[24][1] = "Blue Jeans for Babies Dance";
		}
		findRegistrations(p);
		
		model.setData(data);
		model.setColumns(columnNames);
        table = new JTable(model);
		model = new MyTableModel(data,columnNames);
        
		formatTable(table);
		
        this.add(scrollPane);
	}
	/**
	 * @param table
	 * JTable to format
	 */
	public void formatTable(JTable table){
		if(data.length > 0)
			table.setAutoCreateRowSorter(true);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,300));
		
        table.getColumnModel().getColumn(0).setPreferredWidth(90);
	}
	/**
	 * @param p
	 * Finds what workshops a specific participant is registered for and adds the workshop to the participant schedule
	 */
	public void findRegistrations(Participant p){ //inserts workshops into the participants schedule
		ArrayList<String>registrations = ReadFromFile.getRegistrationList();
		for(int i = 0; i < registrations.size();i++){ //goes through WKSHP_REGISTRATIONS and finds participants registered to workshops
			String line = registrations.get(i);
			for(int j = 5; j < line.length(); j++){
				if(line.substring(j,j+1).equals("[")){
					String temp = "";
					int count = j+1;
					while(!line.substring(count,count+1).equals("]")){
						temp += line.substring(count,count+1);
						count++;
					}
					if(temp.equals(p.getID())){ //finds the participant registered to the workshop
						Workshop w = ReadFromFile.getWorkshopByID(line.substring(1,4));
						addWorkshopToSchedule(w);
					}
				}
			}
		}
	}
	/**
	 * @param w
	 * Workshop to add to the participant schedule
	 */
	public void addWorkshopToSchedule(Workshop w){
		Workshop workshop = w;
		for(int i = 0; i < data.length; i++){
			if(data[i][0].equals(workshop.getTime())){
				if(day == 1 && workshop.getDate().equals(conference.getStartDate())){
					data[i][1] = w.getTitle();
				}else if(day == 2 && workshop.getDate().equals(conference.getEndDate())){
					data[i][1] = w.getTitle();
				}
			}
		}
	}
	/**
	 * @param i 
	 * i value from the for loop to be converted into a readable time 
	 * @return
	 */
	public String convertToDigital(int i){
		String s = "";
		String modifier ="";
		double temp = ((i+18)/2.0)%12;
		
		if(temp%12 < 1)
			temp += 12;
		
		if((temp)%1 == 0.0){
			modifier += ":00";
		}else{
			modifier += ":30";
		}
		if(i > 5){
			modifier += " PM";
		}else{
			modifier += " AM";
		}
		s = (int)temp + modifier;
		return s;
	}
}
