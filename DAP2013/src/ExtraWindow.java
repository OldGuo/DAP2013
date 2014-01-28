import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class ExtraWindow extends JDialog{
	private String windowType;
	
	public ExtraWindow(String t){
		super.setTitle(t);
		this.setSize(1000,450);
		this.setLocationRelativeTo(null);
		windowType = t;
		createWindow();
	}
	public void createWindow(){
		JTabbedPane tabbedPane = new JTabbedPane();
		if(windowType.equals("Conference Participants")){
			createParticipantReport();
		}else if(windowType.equals("Participant List for Each Workshop")){
			createWorkshopReport();
		}else if(windowType.equals("Participant Schedule")){
			createScheduleReport();
		}else if(windowType.equals("Registration Instructions")){
			createInstructionReport();
		}
	}
	public void createInstructionReport(){
		JLabel temp = new JLabel("PUT SOME IMAGES OR SOMETHING SHOWING HOW TO USE THE WINDOWS, NEED TO GET THE FINAL LOOK TO MAKE SCREENSHOTS THOUGH");
		this.add(temp);
	}
	public void createScheduleReport(){
		JTabbedPane tabbedPane = new JTabbedPane();
		ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		ArrayList<Object>participants = read.getData();
		
		ReadFromFile read2 = new ReadFromFile("CONFERENCES");
		ArrayList<Object>conferences = read2.getData();
		
		for(int i = 0; i < participants.size(); i++){
			Participant p = (Participant)participants.get(i);
			Conference c = null;
			for(int j = 0; j < conferences.size(); j++){
				Conference temp = (Conference)conferences.get(j);
				if(p.getCode().equals(temp.getCode())){
					c = (Conference)conferences.get(j);
				}
			}
			JPanel temp = new JPanel();
			
			temp.add(new JLabel(c.getStartDate() + " to " + c.getEndDate()));

			tabbedPane.add(temp,p.getFirstName() + " " + p.getLastName());
		}
		this.add(tabbedPane);
	}
	public void createWorkshopReport(){
		//WORKSHOP REGISTRATIONS
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		JPanel registrations = new JPanel();
		
		ReadFromFile read = new ReadFromFile("WORKSHOPS");
		ArrayList<Object>workshops = read.getData();
		
		for(int i = 0; i < workshops.size(); i++){
			Workshop w = (Workshop)workshops.get(i);
			JPanel temp = new JPanel();
			tabbedPane.add(w.getTitle(),temp);
			createTable(w);
		}
		this.add(tabbedPane);
	}
	public void createParticipantReport(){
		//create schedules for each participant
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		ReadFromFile read = new ReadFromFile("CONFERENCES");
		ArrayList<Object>conferences = read.getData();
		
		for(int i = 0; i < conferences.size(); i++){
			Conference c = (Conference)conferences.get(i);
			JPanel temp = new ParticipantPanel(c.getCode());
			tabbedPane.add(temp,c.getLocation() + "; " + c.getStartDate() + " - " + c.getEndDate());
		}
		this.add(tabbedPane);
	}
	public void createTable(Workshop w){
		Object [][] data;
		if(windowType.equals("Participant List for Each Workshop")){
			//table list of participants registered from the workshops - from WKSHP_REGISTRATIONS
			Workshop workshop = w;
			int numParticipants = 0;
			
			ReadFromFile read = new ReadFromFile("WKSHP_REGISTRATIONS");
			ArrayList<String>registrations = read.getRegistrationList();
			
			String [] columnNames = {"First","Last","Chapter"};
			data = new Object[0][4];
		
			for(int i = 0; i < registrations.size(); i++){ //lines of WKSHP_REGISTRATIONS
				String line = registrations.get(i);
				if(line.substring(1,4).equals(w.getID())){ //gets the registered ID's
					for(int j = 5; j < line.length(); j++){
						if(line.substring(j,j+1).equals("[")){
							String temp = "";
							int count = j+1;
							while(!line.substring(count,count+1).equals("]")){
								temp += line.substring(count,count+1);
								count++;
							}
							System.out.println(w.getID() + ":" + temp);
							numParticipants++;
						}
					}
				}
			}
		}else if(windowType.equals("Participant Schedule")){
			//table of the schedule
			//WORKSHOP ARE 45 MINUTES LONG
			//Day 1: 1PM - 3PM (Workshops)
			/* Registration 11 AM, 7 PM
			 * Opening General Session 9PM 	
			 * */
			//Day 2: 9AM - 3:30 PM
			/* Closing General Session 5 PM
			 * Blue Jeans for babies dance 9 PM
			 * */
			String [] columnNames = {"Time","Event"};
			data = new Object[0][4];
		}
	}
}
