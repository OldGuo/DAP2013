import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ParticipantRegistrationWindow extends JDialog implements ActionListener{
	private final JTable table;
	private Object[]data;
	private ArrayList<Participant>participants;
	private DefaultTableModel model;
	private final JScrollPane scrollPane;
	private final JButton saveButton;

	public ParticipantRegistrationWindow(){
		this.setLayout(new FlowLayout());
		table = new MyTableModel();
		MouseListener MyMouseListener = new MyMouseListener();
		table.addMouseListener(MyMouseListener);
		saveButton = new JButton("Save Changes");
		saveButton.addActionListener(this);
		saveButton.setActionCommand("save");
		this.setSize(900,450);
		loadData();
		createTable();
		scrollPane = new JScrollPane(table);
	    this.add(saveButton);
	    this.add(scrollPane);
	}
	public void loadData(){
		participants = ReadFromFile.getParticipants();
	}
	public void createTable(){
		String [] columnNames = {"Type","First","Last","Chapter","Register"};
        model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnNames);
		data = new Object[5];
		for(int i = 0; i < participants.size();i++){
			data[0]= participants.get(i).getType().toString();
			data[1]= participants.get(i).getFirstName().toString();
			data[2]= participants.get(i).getLastName().toString();
			data[3]= participants.get(i).getChapter().toString();
			data[4] = new Boolean(false);
			model.addRow(data);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	    if ("save".equals(e.getActionCommand())){
	    	Register();
	    	this.dispose();
	    }
	}
	public void Register(){
		String info;
		for(int i = 0; i < table.getRowCount();i++){
			for(int j = 0; j < table.getColumnCount();j++){
				if(model.getValueAt(i, j).equals(Boolean.TRUE)){
					info = model.getValueAt(i, 0) + " " + model.getValueAt(i, 1) +
							" " + model.getValueAt(i, 2) + " " + model.getValueAt(i, 3);
					System.out.println(info);
				}
			}
		}
	}
	public class MyMouseListener extends MouseAdapter{
		@Override
		public void mouseReleased(MouseEvent e) {
	        Point p = e.getPoint();
	        int row = table.rowAtPoint(p);
	        int col = table.columnAtPoint(p);
	       	//System.out.println(model.getValueAt(row, col));
	    }
	}
}
