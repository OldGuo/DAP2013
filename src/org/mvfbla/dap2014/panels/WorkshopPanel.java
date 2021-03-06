package org.mvfbla.dap2014.panels;


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
import javax.swing.table.TableColumnModel;

import org.mvfbla.dap2014.base.Workshop;
import org.mvfbla.dap2014.utilities.MyTableModel;
import org.mvfbla.dap2014.utilities.ReadFromFile;
import org.mvfbla.dap2014.windows.ParticipantRegistrationWindow;


/**
 * @author Young
 * Creates an organized table for users to view workshops and preregister participants
 */
public class WorkshopPanel extends JPanel{
	private JScrollPane scrollPane;
	private Object[][]data;
	private MyTableModel model;
	private JTable table;
	private ArrayList<Object>workshops;
	private final JFrame dialogFrame;
	
	public WorkshopPanel(){
		dialogFrame = new JFrame();
		JLabel label = new JLabel("Double Click for Descriptions, Right Click to Register");
		model = new MyTableModel();
        this.add(label);
        loadData();
		createTable();
	}
	public void loadData(){
		ReadFromFile read = new ReadFromFile("WORKSHOPS");
		workshops = ReadFromFile.getData("WORKSHOPS");
	}
	/**
	 * Creates a table from the information grabbed from the workshops text file
	 */
	public void createTable(){
		//Creates a table from an arraylist populated from the file WORKSHOPS.txt
		String [] columnNames = {"Location","Title","Date","Time"};
		data = new Object[workshops.size()][4];
		
		for(int i = 0; i < workshops.size();i++){
			Workshop w = (Workshop) workshops.get(i);
			for(int j = 0; j < 4; j++){
				data[i][0]= w.getCode().toString();
				data[i][1]= w.getTitle().toString();
				data[i][2]= w.getDate().toString();
				data[i][3]= w.getTime().toString();
			}
		}
		model.setData(data);
		model.setColumns(columnNames);
        table = new JTable(model);
		model = new MyTableModel(data,columnNames);
        
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        
        scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000,575));
        this.add(scrollPane);
        
		TableColumnModel t = table.getColumnModel();
		t.getColumn(0).setPreferredWidth(90);
		t.getColumn(1).setPreferredWidth(1000);
		t.getColumn(2).setPreferredWidth(75);
		t.getColumn(3).setPreferredWidth(75);

		MouseListener MyMouseListener = new MyMouseListener();
		table.addMouseListener(MyMouseListener);
	}
	public void createDialog(MouseEvent e){
		//opens the window showing the description
		
		Point p = e.getPoint();
		
		String code = (String) table.getValueAt(table.rowAtPoint(p), 0);
		String title = (String) table.getValueAt(table.rowAtPoint(p), 1);;
		String date = (String) table.getValueAt(table.rowAtPoint(p), 2);;
		String time = (String) table.getValueAt(table.rowAtPoint(p), 3);;
		Workshop w = ReadFromFile.getWorkshopByInfo(code, title, date, time);
		
		String name = w.getTitle();
		String descrip = w.getDescrip();
		
		JTextArea t = new JTextArea(descrip);
		t.setLineWrap(true);
		t.setWrapStyleWord(true);
		t.setEditable(false);
		JOptionPane optionPane = new JOptionPane(t);
		JDialog dialog = optionPane.createDialog(dialogFrame,name);
		dialog.setSize(new Dimension(500,250));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	public void createPopup(MouseEvent e){
		//register participants for the workshop using right click
		//creates the little thing that asks for to register
		
		Point p = e.getPoint();
		String code = (String) table.getValueAt(table.rowAtPoint(p), 0);
		String title = (String) table.getValueAt(table.rowAtPoint(p), 1);;
		String date = (String) table.getValueAt(table.rowAtPoint(p), 2);;
		String time = (String) table.getValueAt(table.rowAtPoint(p), 3);;
		Workshop workshop = ReadFromFile.getWorkshopByInfo(code, title, date, time);
        
		//popup asking for registration
		if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
            JPopupMenu popup = new JPopupMenu();
            popup = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("Register Participants: " + title);
            menuItem.addActionListener(new MenuActionListener(workshop));
            popup.add(menuItem);
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
	}
	public class MyMouseListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			//Double click to show description
			if (e.getClickCount() > 1) {
				createDialog(e);
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			//Shows the option to register participants
	        createPopup(e);
	    }
	}
	public class MenuActionListener implements ActionListener{
		//opens the participation registration window 
		Workshop workshop;
		public MenuActionListener(Workshop w) {
			workshop = w;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//opens the ParticipantRegistrationWindow
			JDialog dialog = new ParticipantRegistrationWindow(workshop);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
	}
}
