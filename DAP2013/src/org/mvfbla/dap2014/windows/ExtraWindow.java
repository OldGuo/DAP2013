package org.mvfbla.dap2014.windows;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.mvfbla.dap2014.base.Conference;
import org.mvfbla.dap2014.base.Participant;
import org.mvfbla.dap2014.base.Workshop;
import org.mvfbla.dap2014.panels.InstructionPanel;
import org.mvfbla.dap2014.panels.ParticipantPanel;
import org.mvfbla.dap2014.panels.SchedulePanel;
import org.mvfbla.dap2014.panels.WorkshopRegistrationsPanel;
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
		if(windowType.equals("Conference Participants")){
			createParticipantReport();
		}else if(windowType.equals("Participant List for Each Workshop")){
			createWorkshopReport();
		}else if(windowType.equals("Participant Schedules")){
			createScheduleReport();
		}else if(windowType.equals("General Instructions")){
			createInstructionReport();
		}
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
			JPanel temp = new WorkshopRegistrationsPanel(w);
		
			tabbedPane.add(temp,w.getTitle() + ", " + w.getCode());
		}
		this.add(tabbedPane);
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
	 * Creates an instruction window
	 */
	public void createInstructionReport(){
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add(new InstructionPanel(1), "Workshop Registration Help");
		tabbedPane.add(new InstructionPanel(2), "Participant Registration Help");
		tabbedPane.add(new InstructionPanel(3), "Reports Help");
		this.add(tabbedPane);
	}
}
