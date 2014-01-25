//

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class NavigationBar extends JMenuBar implements ActionListener{
	//menus
	JMenuBar menuBar;
	JMenu menu, menu2;
	JMenuItem menuItem,menuItem2,menuItem3,menuItem4;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;

	public NavigationBar(){
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Reports");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Conference Participants");
		menuItem.addActionListener(this);
		
		menuItem2 = new JMenuItem("Participant List for Each Workshop");
		menuItem2.addActionListener(this);
		
		menuItem3 = new JMenuItem("Participant Schedule");
		menuItem3.addActionListener(this);
		
		menu.add(menuItem);
		menu.add(menuItem2);
		menu.add(menuItem3);
	
		menu2 = new JMenu("Help");
		menuBar.add(menu2);
		
		menuItem4 = new JMenuItem("Registration Instructions");
		menuItem4.addActionListener(this);
		
		menu2.add(menuItem4);
		
	}
	public JMenuBar getMenu(){
		return menuBar;
	}
	public void actionPerformed(ActionEvent e) {
		String windowType = e.getActionCommand();
		JDialog dialog = new ExtraWindow(windowType);
		dialog.setVisible(true);
	}
}
