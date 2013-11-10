import javax.swing.JPanel;
import javax.swing.JTable;


public class WorkshopPanel extends JPanel{
	public WorkshopPanel(){
		//JScrollPane scrollFrame = new JScrollPane(this);
		//scrollFrame.add(this);

		JTable table = new JTable(50,5);
		this.add(table);
	}
}
