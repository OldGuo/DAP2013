package org.mvfbla.dap2014.windows;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.mvfbla.dap2014.base.Conference;
import org.mvfbla.dap2014.base.Participant;
import org.mvfbla.dap2014.base.Workshop;
import org.mvfbla.dap2014.panels.ParticipantPanel;
import org.mvfbla.dap2014.panels.SchedulePanel;
import org.mvfbla.dap2014.utilities.MyTableModel;
import org.mvfbla.dap2014.utilities.ReadFromFile;


/**
 * @author Young
 * ExtraWindow creates another window to show conference reports
 */
public class ExtraWindow extends JDialog{
	private String windowType;
	
	/**
	 * @param t
	 * The type of the window to create
	 */
	public ExtraWindow(String t){
		super.setTitle(t);
		this.setSize(1000,450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		
		windowType = t;
		createWindow();
	}
	/**
	 * Defines which window to create based on the window type
	 */
	public void createWindow(){
		JTabbedPane tabbedPane = new JTabbedPane();
		if(windowType.equals("Conference Participants")){
			createParticipantReport();
		}else if(windowType.equals("Participant List for Each Workshop")){
			createWorkshopReport();
		}else if(windowType.equals("Participant Schedules")){
			//System.out.println("got here?");
			createScheduleReport();
		}else if(windowType.equals("Registration Instructions")){
			createInstructionReport();
		}
	}
	/**
	 * Creates an instruction window
	 */
	public void createInstructionReport(){
		JLabel temp = new JLabel("Instructions");
		this.add(temp);
	}
	/**
	 * Creates schedules for each registered participant
	 */
	public void createScheduleReport(){
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		ArrayList<Object>participants = ReadFromFile.getData("PARTICIPANTS");
		ArrayList<Object>conferences = ReadFromFile.getData("CONFERENCES");
		
		for(int i = 0; i < participants.size(); i++){
			Participant p = (Participant)participants.get(i);
			Conference c = null;
			JTabbedPane inner = new JTabbedPane();
			for(int j = 0; j < conferences.size(); j++){
				Conference temp = (Conference)conferences.get(j);
				if(p.getCode().equals(temp.getCode())){
					c = (Conference)conferences.get(j);
				}
			}
			JPanel dayOne = new SchedulePanel(p,c,1);
			
			JPanel dayTwo = new SchedulePanel(p,c,2);
			
			inner.add(dayOne,c.getStartDate());
			inner.add(dayTwo,c.getEndDate());
			
			tabbedPane.add(inner,p.getFirstName() + " " + p.getLastName() + ", " + p.getCode());
		}
		this.add(tabbedPane);
	}
	/**
	 * Creates a report showing which participants are registered to each workshop
	 */
	public void createWorkshopReport(){
		//WORKSHOP REGISTRATIONS, Create a table showing each participant for each schedule
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		JPanel registrations = new JPanel();
		
		ArrayList<Object>workshops = ReadFromFile.getData("WORKSHOPS");
		
		for(int i = 0; i < workshops.size(); i++){
			Workshop w = (Workshop)workshops.get(i);
			JPanel temp = new JPanel();
			tabbedPane.add(w.getTitle() + ", " + w.getCode(),temp);
			temp.add(createTable(w));
		}
		this.add(tabbedPane);
	}
	/**
	 * Creates a list of participants registered to each specific conference
	 */
	public void createParticipantReport(){
		//create schedules for each participant
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		ArrayList<Object>conferences = ReadFromFile.getData("CONFERENCES");
		
		for(int i = 0; i < conferences.size(); i++){
			Conference c = (Conference)conferences.get(i);
			JPanel temp = new ParticipantPanel(c.getCode());
			tabbedPane.add(temp,c.getLocation() + "; " + c.getStartDate() + " - " + c.getEndDate());
			//createTable();
		}
		this.add(tabbedPane);
	}
	/**
	 * @param o
	 * the object to create a table for
	 * @return
	 * the scroll pane containing the completed table
	 */
	public JScrollPane createTable(Object o){
		Object [][] data = null;
		String [] columnNames = null;
		MyTableModel model = new MyTableModel();
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		
		if(windowType.equals("Participant List for Each Workshop")){
			//table list of participants registered from the workshops - from WKSHP_REGISTRATIONS
			Workshop workshop = (Workshop)o;
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
			data = new Object[numParticipants][4];

			for(int i = 0; i < participants.size();i++){
				Participant part = ReadFromFile.getParticipantByID(participants.get(i));
				data[i][0] = part.getType();
				data[i][1] = part.getFirstName();
				data[i][2] = part.getLastName();
				data[i][3] = part.getCode();
			}
			//get the data
		}
		model.setData(data);
		model.setColumns(columnNames);
        table = new JTable(model);
        model = new MyTableModel(data,columnNames);
        
		if(data.length > 0)
			table.setAutoCreateRowSorter(true);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,300));
		
        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        
		return scrollPane;
	}
}
