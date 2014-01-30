package org.mvfbla.dap2014.utilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.mvfbla.dap2014.windows.ExtraWindow;

/**
 * @author Young
 * Navigation bar to show the reports and help options
 */
public class NavigationBar extends JMenuBar implements ActionListener{
	//menus
	private JMenuBar menuBar;
	private JMenu reportMenu, helpMenu;
	private JMenuItem participantItem,workshopItem,scheduleItem,helpItem;
	
	/**
	 * Creates the navigation bar for the program
	 */
	public NavigationBar(){
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		reportMenu = new JMenu("Reports");

		//a group of JMenuItems
		participantItem = new JMenuItem("Conference Participants");
		participantItem.addActionListener(this);
		
		workshopItem = new JMenuItem("Participant List for Each Workshop");
		workshopItem.addActionListener(this);
		
		scheduleItem = new JMenuItem("Participant Schedules");
		scheduleItem.addActionListener(this);
		
		reportMenu.add(participantItem);
		reportMenu.add(workshopItem);
		reportMenu.add(scheduleItem);
	
		helpMenu = new JMenu("Help");
		
		helpItem = new JMenuItem("Registration Instructions");
		helpItem.addActionListener(this);
		
		helpMenu.add(helpItem);
		
		menuBar.add(reportMenu);
		menuBar.add(helpMenu);
	}
	/**
	 * @return
	 * the created menu bar
	 */
	public JMenuBar getMenu(){
		return menuBar;
	}
	public void actionPerformed(ActionEvent e) {
		//Creates a window based on the menu chosen
		String windowType = e.getActionCommand();
		JDialog dialog = new ExtraWindow(windowType);
		dialog.setVisible(true);
	}
}
