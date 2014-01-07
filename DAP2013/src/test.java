import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class test implements ActionListener {
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DAP2013");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000,600));

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel workshopPanel = new WorkshopPanel();

        JPanel registrationPanel = new RegistrationPanel();

        JPanel participantPanel = new ParticipantPanel();

        tabbedPane.addTab("View Registered Participants",participantPanel);
		tabbedPane.addTab("Register for Workshops",workshopPanel);
		tabbedPane.addTab("Register Members",registrationPanel);

        NavigationBar nav = new NavigationBar();
		frame.setJMenuBar(nav.getMenu());
		frame.add(tabbedPane);

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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
