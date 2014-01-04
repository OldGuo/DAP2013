import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class WorkshopPanel extends JPanel{
	private final JScrollPane scrollPane;
	private Object[]data;
	private DefaultTableModel model;
	private JTable table;
	private ArrayList<Object>workshops;
	private final JFrame dialogFrame;

	public WorkshopPanel(){
		dialogFrame = new JFrame();
		JLabel label = new JLabel("Double Click for Descriptions, Right Click to Register");
		loadData();
		createTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900,450));
        this.add(label);
        this.add(scrollPane);
	}
	public void loadData(){
		ReadFromFile read = new ReadFromFile("WORKSHOPS");
		workshops = read.getData();
	}
	public void createTable(){
		//Creates a table from an arraylist populated from the file WORKSHOPS.txt
		String [] columnNames = {"Location","Title","Date","Time"};
		table = new MyTableModel();
		table.getTableHeader().setReorderingAllowed(false);
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);
		data = new String[4];
		for(int i = 0; i < workshops.size();i++){
			Workshop w = (Workshop) workshops.get(i);
			data[0]= w.getCode().toString();
			data[1]= w.getTitle().toString();
			data[2]= w.getDate().toString();
			data[3]= w.getTime().toString();
			model.addRow(data);
		}
		TableColumnModel t = table.getColumnModel();
		t.getColumn(0).setPreferredWidth(75);
		t.getColumn(1).setPreferredWidth(1000);
		t.getColumn(2).setPreferredWidth(75);
		t.getColumn(3).setPreferredWidth(75);

		MouseListener MyMouseListener = new MyMouseListener();
		table.addMouseListener(MyMouseListener);
	}
	public void createDialog(MouseEvent e){
		//opens the window showing the description
		Point p = e.getPoint();
		String name = ((Workshop)workshops.get(table.rowAtPoint(p))).getTitle();
		String descrip = ((Workshop)workshops.get(table.rowAtPoint(p))).getDescrip();
		JTextArea t = new JTextArea(descrip);
		t.setLineWrap(true);
		t.setWrapStyleWord(true);
		t.setEditable(false);
		JOptionPane optionPane = new JOptionPane(t);
		JDialog dialog = optionPane.createDialog(dialogFrame,name);
		dialog.setSize(new Dimension(500,250));
		dialog.setVisible(true);
	}
	public void createPopup(MouseEvent e){
		//register participants for the workshop using right click
		Point p = e.getPoint();
		Workshop workshop = (Workshop)workshops.get(table.rowAtPoint(p));
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
            JPopupMenu popup = new JPopupMenu();
            popup = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("Register Participants");
            menuItem.addActionListener(new MenuActionListener(workshop));
            popup.add(menuItem);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
	}
	public class MyMouseListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() > 1) {
				createDialog(e);
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
	        createPopup(e);
	    }
	}
	public class MenuActionListener implements ActionListener{
		Workshop workshop;
		public MenuActionListener(Workshop w) {
			workshop = w;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//opens the ParticipantRegistrationWindow
			JDialog dialog = new ParticipantRegistrationWindow(workshop);
			dialog.setVisible(true);
		}
	}
}
