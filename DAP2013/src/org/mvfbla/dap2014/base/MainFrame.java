package org.mvfbla.dap2014.base;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.mvfbla.dap2014.panels.RegistrationPanel;
import org.mvfbla.dap2014.panels.WorkshopPanel;
import org.mvfbla.dap2014.utilities.NavigationBar;

/**
 * @author Young
 * Main JFrame of the program
 */
public class MainFrame implements ActionListener {
	/**
	 * Creates all main panels and tabs for the program
	 */
	private static void createAndShowGUI() {
        //Create and set up the window.
		
		JFrame frame = new JFrame("DAP2013");
		frame.setIconImage(new ImageIcon("Files\\fbla_large.png").getImage());
		
		frame.setPreferredSize(new Dimension(1080,720));
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel registrationPanel = new RegistrationPanel();
        
        JPanel workshopPanel = new WorkshopPanel();

		tabbedPane.addTab("Register for Workshops",workshopPanel);
		tabbedPane.addTab("Register Members",registrationPanel);
		
        NavigationBar nav = new NavigationBar();
		frame.setJMenuBar(nav.getMenu());
		frame.add(tabbedPane);
		
		frame.pack();
        frame.setLocationRelativeTo(null);
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
