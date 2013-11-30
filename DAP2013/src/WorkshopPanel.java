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


public class WorkshopPanel extends JPanel{
	private final JScrollPane scrollPane;
	private Object[]data;
	private DefaultTableModel model;
	private JTable table;
	private ArrayList<Workshop>workshops;
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
		workshops = ReadFromFile.getWorkshops();
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

		MouseListener MyMouseListener = new MyMouseListener();
		table.addMouseListener(MyMouseListener);
	}
	public void createDialog(MouseEvent e){
		Point p = e.getPoint();
		String name = workshops.get(table.rowAtPoint(p)).getTitle();
		String descrip = workshops.get(table.rowAtPoint(p)).getDescrip();
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
		int r = table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < table.getRowCount()) {
            table.setRowSelectionInterval(r, r);
        } else {
            table.clearSelection();
        }

        int rowindex = table.getSelectedRow();
        if (rowindex < 0)
            return;
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
            JPopupMenu popup = new JPopupMenu();
            popup = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("Register Participants");
            menuItem.addActionListener(new MenuActionListener());
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
		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog dialog = new ParticipantRegistrationFrame();
			dialog.setVisible(true);
		}
	}
}
