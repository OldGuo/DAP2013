import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class ParticipantPanel extends JPanel implements ItemListener{

	private final JScrollPane scrollPane;
	private final JTable table;
	private Object[]data;
	private DefaultTableModel model;
	private ArrayList<Object>participants;
	private final JCheckBox DC, LA, MN;
	private final TableRowSorter<MyTableModel> sorter;

	// Sort by:
	// Participant type, last name
	// chapter number, participant type, last name (generate a chapter registration confirmation)
	// each chapter should begin on a new page
	// http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableFilterDemoProject/src/components/TableFilterDemo.java
	//
	public ParticipantPanel(){
		setLayout(new FlowLayout());
		DC = new JCheckBox("Show DC Participants");
		DC.addItemListener(this);

		LA = new JCheckBox("Show LA Participants");
		LA.addItemListener(this);

		MN = new JCheckBox("Show MN Participants");
		MN.addItemListener(this);

		//table = new CustomTable();


		DC.setSelected(true);
		LA.setSelected(true);
		MN.setSelected(true);

		this.add(DC);
		this.add(LA);
		this.add(MN);

		MyTableModel model = new MyTableModel();
        sorter = new TableRowSorter<MyTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900,450));
        this.add(scrollPane);
		//createTable();
	}
	/*public void createTable(){
		ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		participants = read.getData();

		//filter the t able based on the states of the checkboxes
		filterParticipants();

		String [] columnNames = {"Type","First","Last","Chapter"};
        model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnNames);
        model.setRowCount(0);

		data = new Object[4];
		for(int i = 0; i < participants.size();i++){
			Participant p = (Participant)participants.get(i);
			data[0]= p.getType().toString();
			data[1]= p.getFirstName().toString();
			data[2]= p.getLastName().toString();
			data[3]= p.getCode().toString(); //change back to chapter later
			model.addRow(data);
		}
		this.add(scrollPane);
	}*/
	public void filterParticipants(){
		for(int i = 0; i < participants.size();i++)
		{
			Participant p = (Participant)participants.get(i);

			if(p.getCode().equals("DC") && !DC.isSelected()){
				participants.remove(i);
				i--;
			}
			if(p.getCode().equals("MN") && !MN.isSelected()){
				participants.remove(i);
				i--;
			}
			if(p.getCode().equals("LA") && !LA.isSelected()){
				participants.remove(i);
				i--;
			}
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		//createTable();
	}
	class MyTableModel extends AbstractTableModel {
        private final String[] columnNames = {"First Name",
                                        "Last Name",
                                        "Sport",
                                        "# of Years",
                                        "Vegetarian"};
        private final Object[][] data = {
        {"Kathy", "Smith",
         "Snowboarding", new Integer(5), new Boolean(false)},
        {"John", "Doe",
         "Rowing", new Integer(3), new Boolean(true)},
        {"Sue", "Black",
         "Knitting", new Integer(2), new Boolean(false)},
        {"Jane", "White",
         "Speed reading", new Integer(20), new Boolean(true)},
        {"Joe", "Brown",
         "Pool", new Integer(10), new Boolean(false)}
        };
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
}
