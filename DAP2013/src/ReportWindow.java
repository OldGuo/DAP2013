import javax.swing.JDialog;
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
			for(int i = 0; i < 40; i++){
				JPanel temp = new JPanel();
				tabbedPane.add(temp,"Space");
			}
		}
		this.add(tabbedPane);
	}
}
