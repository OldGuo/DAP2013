import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class WorkshopPanel extends JPanel{
	private JScrollPane scrollPane;
	private Object[]data;
	private DefaultTableModel model;
	private JTable table;
	
	public WorkshopPanel(){
		String [] columnNames = {"Location","Title","Description","Date","Time"};
		table = new JTable(new DefaultTableModel());
        
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
}
