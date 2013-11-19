import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class WorkshopPanel extends JPanel{
	private final JScrollPane scrollPane;
	private Object[]data;
	private final DefaultTableModel model;
	private final JTable table;

	public WorkshopPanel(){
		String [] columnNames = {"Location","Title","Description","Date","Time"};
		table = new JTable(){
			 @Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			        Component c = super.prepareRenderer(renderer, row, column);
			        if (c instanceof JComponent) {
			            JComponent jc = (JComponent) c;
			            jc.setToolTipText(getValueAt(row, column).toString());
			        }
			        return c;
			    }
		};
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);
		loadData();

        scrollPane = new JScrollPane(table);
        this.add(scrollPane);
	}
	public void loadData(){
		ReadFromFile r = new ReadFromFile();
		ArrayList<Workshop>workshops = r.getWorkshops();
		data = new String[5];
		for(int i = 0; i < workshops.size();i++){
			data[0]= workshops.get(i).getCode().toString();
			data[1]= workshops.get(i).getTitle().toString();
			data[2]= workshops.get(i).getDescrip().toString();
			data[3]= workshops.get(i).getDate().toString();
			data[4]= workshops.get(i).getTime().toString();
			model.addRow(data);
		}
	}
	public class MyTableModel extends DefaultTableModel {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
}
