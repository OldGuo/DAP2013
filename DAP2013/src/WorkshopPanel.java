import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolTip;
import javax.swing.table.DefaultTableModel;


public class WorkshopPanel extends JPanel{
	private final JScrollPane scrollPane;
	private Object[]data;
	private DefaultTableModel model;
	private JTable table;
	private ArrayList<Workshop>workshops;
	private final JFrame dialogFrame;

	public WorkshopPanel(){
		//this.setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new JPanel();

        JPanel panel2 = new JPanel();

        JPanel panel3 = new JPanel();
        tabbedPane.addTab("DC",panel1);
        tabbedPane.addTab("MN",panel2);
        tabbedPane.addTab("LA",panel3);
        this.add(tabbedPane);
		dialogFrame = new JFrame();
		JLabel label = new JLabel("Double Click for Descriptions");
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

		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					Point p = e.getPoint();
					JTextArea t = new JTextArea(workshops.get(table.rowAtPoint(p)).getDescrip());
					t.setLineWrap(true);
					t.setWrapStyleWord(true);
					t.setEditable(false);
					JOptionPane optionPane = new JOptionPane(t);
					JDialog dialog = optionPane.createDialog(dialogFrame,"Description");
					dialog.setSize(new Dimension(300,250));
					dialog.setVisible(true);
				}
			}
		});
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
	}
}
