import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
		private Object[][]data;
		private String [] columnNames;
		
		public MyTableModel(){}
		
		public void setData(Object[][]d){
			data = d;
		}
		public void setColumns(String[]c){
			columnNames = c;
		}
        @Override
		public int getColumnCount() {
            return columnNames.length;
        }
        @Override
		public int getRowCount() {
            return data.length;
        }
        @Override
		public String getColumnName(int col) {
            return columnNames[col];
        }
        @Override
		public Object getValueAt(int row, int col) {
            return data[row][col];
        }
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @Override
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
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
	}