import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class ExtraWindow extends JDialog{
	private String reportType;
	
	public ExtraWindow(String t){
		super.setTitle(t);
		this.setSize(1000,450);
		this.setLocationRelativeTo(null);
		reportType = t;
		createReport();
	}
	public void createReport(){
		JTabbedPane tabbedPane = new JTabbedPane();
		if(reportType.equals("Conference Participants")){
			createParticipantReport();
		}else if(reportType.equals("Participant List for Each Workshop")){
			createWorkshopReport();
		}else if(reportType.equals("Participant Schedule")){
			createScheduleReport();
		}
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
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel registrations = new JPanel();
		
		ReadFromFile read = new ReadFromFile("WORKSHOPS");
		ArrayList<Object>workshops = read.getData();
		
		for(int i = 0; i < workshops.size(); i++){
			Workshop w = (Workshop)workshops.get(i);
			JPanel temp = new JPanel();
			tabbedPane.add(w.getTitle(),temp);
		}
		this.add(tabbedPane);
	}
	public void createParticipantReport(){
		JTabbedPane tabbedPane = new JTabbedPane();
		ReadFromFile read = new ReadFromFile("CONFERENCES");
		ArrayList<Object>conferences = read.getData();
		
		for(int i = 0; i < conferences.size(); i++){
			Conference c = (Conference)conferences.get(i);
			JPanel temp = new ParticipantPanel(c.getCode());
			tabbedPane.add(temp,c.getLocation() + "; " + c.getStartDate() + " - " + c.getEndDate());
		}
		this.add(tabbedPane);
	}
}
