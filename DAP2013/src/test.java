import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class test {
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DAP2013");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,500));

        JTabbedPane layout = new JTabbedPane(SwingConstants.TOP);
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new JPanel();
        JLabel emptyLabel = new JLabel("label");
        emptyLabel.setPreferredSize(new Dimension(500, 500));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        panel1.add(emptyLabel);
        JPanel panel2 = new JPanel();
		JTextField name = new JTextField(20);
		panel2.add(name);
        JPanel panel3 = new JPanel();

		tabbedPane.addTab("Label",panel1);
		tabbedPane.addTab("Text Field",panel2);
		tabbedPane.addTab("Tab3",panel3);


        NavigationBar nav = new NavigationBar();
		frame.setJMenuBar(nav.getMenu());
		frame.add(tabbedPane);
		FileParser f = new FileParser();

		f.getWorkhops();
		//Display the window.

        /*String [] columnNames = {"Title","Description","Date","Time"};

		JTable table = new JTable(5,5);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);*/

		//textfield testing
		frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
			public void run() {
                createAndShowGUI();
            }
        });
    }
}
