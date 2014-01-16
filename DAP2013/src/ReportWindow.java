import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class ReportWindow extends JDialog{
	private String reportType;
	
	public ReportWindow(String t){
		super.setTitle(t);
		this.setSize(1000,450);
		reportType = t;
		createReport();
	}
	public void createReport(){
		JTabbedPane tabbedPane = new JTabbedPane();
		if(reportType.equals("Conference Participants")){
			JPanel DC = new JPanel();
			JPanel LA = new JPanel();
			JPanel MN = new JPanel();
			
			tabbedPane.add(DC,"DC");
			tabbedPane.add(LA,"LA");
			tabbedPane.add(MN,"MN");
			
		}else if(reportType.equals("Participant List for Each Workshop")){
			JPanel workshops = new JPanel();
			
			tabbedPane.add(workshops,"Workshop");
		}else if(reportType.equals("Participant Schedule")){
			ReadFromFile read = new ReadFromFile("PARTICIPANTS");
			ArrayList<Object>participants = read.getData();
			
			ReadFromFile read2 = new ReadFromFile("CONFERENCES");
			ArrayList<Object>conferences = read.getData();
			
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

				tabbedPane.add(temp,p.getFirstName() + " " + p.getLastName() + ", " + p.getCode());
			}
		}
		this.add(tabbedPane);
	}
}
