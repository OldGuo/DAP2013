//

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


public class NavigationBar extends JMenuBar{
	//menus
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem,menuItem2,menuItem3;
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
		menu.add(menuItem);
		menuItem2 = new JMenuItem("Participant List for Each Workshop");
		menu.add(menuItem2);
		menuItem3 = new JMenuItem("Participant Schedule");
		menu.add(menuItem3);
	}
	public JMenuBar getMenu(){
		return menuBar;
	}
}
