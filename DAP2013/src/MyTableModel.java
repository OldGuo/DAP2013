import javax.swing.JTable;

public class MyTableModel extends JTable{
	@Override
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col == 4) {
            return true;
        } else {
            return false;
        }
    }
	@Override
	public Class<?> getColumnClass(int columnIndex){
		Class clazz = String.class;
		switch (columnIndex) {
	        case 4:
	        	clazz = Boolean.class;
	        	break;
		}
		return clazz;
	}	
}
