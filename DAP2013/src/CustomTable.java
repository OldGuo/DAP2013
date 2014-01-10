import javax.swing.JTable;

public class CustomTable extends JTable{
	@Override
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
		System.out.println("asdfasdf");
        if (col == 4) {
            return true;
        } else {
            return false;
        }
    }
}
