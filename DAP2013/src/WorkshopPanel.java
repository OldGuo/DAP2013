import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class WorkshopPanel extends JPanel{
	private final JScrollPane scrollPane;
	private Object[]data;
	private DefaultTableModel model;
	private JTable table;
	private ArrayList<Workshop>workshops;

	public WorkshopPanel(){
		JLabel label = new JLabel("Mouse over for descriptions");
		loadData();
		createTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900,450));
        this.add(label);
        this.add(scrollPane);
	}
	public void loadData(){
		ReadFromFile r = new ReadFromFile();
		workshops = r.getWorkshops();
	}
	public void createTable(){
		String [] columnNames = {"Location","Title","Date","Time"};
		table = new MyTableModel();
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);
		data = new String[4];
		for(int i = 0; i < workshops.size();i++){
			data[0]= workshops.get(i).getCode().toString();
			data[1]= workshops.get(i).getTitle().toString();
			data[2]= workshops.get(i).getDate().toString();
			data[3]= workshops.get(i).getTime().toString();
			model.addRow(data);
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(1000);
		table.getColumnModel().getColumn(2).setPreferredWidth(75);
		table.getColumnModel().getColumn(3).setPreferredWidth(75);
	}
	public class MyTableModel extends JTable {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		@Override
		public JToolTip createToolTip(){
			JToolTip tip = new JToolTip();
			return tip;
		}
		@Override
		public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			Component c = super.prepareRenderer(renderer, row, column);
			if (c instanceof JComponent) {
				JComponent jc = (JComponent) c;
				jc.setToolTipText(MultiLineTooltips.splitToolTip(workshops.get(row).getDescrip()));
			}
			return c;
		}
	}
}
