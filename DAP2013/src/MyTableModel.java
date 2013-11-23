import javax.swing.JTable;

public class MyTableModel extends JTable{
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
