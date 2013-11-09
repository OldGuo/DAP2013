import javax.swing.JPanel;
import javax.swing.JTable;


public class WorkshopPanel extends JPanel{
	public WorkshopPanel(){
		JTable table = new JTable(50,5);
		this.add(table);
	}
}
