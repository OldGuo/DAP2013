import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
		tabbedPane.addTab("Tab1",panel1);
		tabbedPane.addTab("Tab2",panel2);
		tabbedPane.addTab("Tab3",panel3);
		getWorkshops();
        JLabel emptyLabel = new JLabel("label");
        emptyLabel.setPreferredSize(new Dimension(500, 500));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        NavigationBar nav = new NavigationBar();

		frame.setJMenuBar(nav.getMenu());
		frame.add(tabbedPane);

		//Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	public static void getWorkshops(){
		ArrayList<String>conferenceInfo;
		Scanner input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WORKSHOPS.txt");

		while(input.hasNextLine()){
			conferenceInfo = new ArrayList<String>(6);
			String line = input.nextLine();
			int count = 0;
			String temp = "";
			int i = 0;
			while(i+1<line.length()){
				temp = "";
				if(line.charAt(i) == '[')
				{
					int j = i;
					while(line.charAt(j+1) != ']'){
						temp += line.charAt(j+1);
						j++;
					}
					conferenceInfo.add(count, temp);
					count++;
				}
				i++;
			}
			Workshop w = new Workshop(conferenceInfo);
			System.out.println(w.getId() + "," + w.getCode() + "," + w.getTitle() + "," + w.getDescrip() + "," + w.getDate() + "," + w.getTime());
		}
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
