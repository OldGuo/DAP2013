package org.mvfbla.dap2014.utilities;
import javax.swing.table.AbstractTableModel;

/**
 * @author Young
 * Custom Table Model used in all tables of the program
 */
public class MyTableModel extends AbstractTableModel {
		private Object[][]data;
		private String [] columnNames;
		
		public MyTableModel(){}

		/**
		 * @param d
		 * 2D array of data to create the table
		 * @param c
		 * 1D array of column names
		 */
		public MyTableModel(Object [][] d, String [] c){
			data = d;
		}
		
		/**
		 * @param d
		 * 2D array of data to create the table
		 */
		public void setData(Object [][] d){
			data = d;
		}
		/**
		 * @param c
		 * 1D array of column names
		 */
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
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
		@Override
		public boolean isCellEditable(int row, int col) {
			if (col == 4) {
                return true;
            } else {
                return false;
            }
		}
	}
