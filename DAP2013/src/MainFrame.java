import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author Young
 * Main JFrame of the program
 */
public class MainFrame implements ActionListener {
	/**
	 * Creates all main panels and tabs for the program
	 */
	public MainFrame(){
		System.out.println("THIS HAS TO RUN");
	}
	private static void createAndShowGUI() {
        //Create and set up the window.
		
		JFrame frame = new JFrame("DAP2013");
		
		//frame.setLayout(new BorderLayout());
		//frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Young\\Desktop\\Teemo_0 - Copy.jpg")));
		//frame.setLayout(new FlowLayout());
		
		frame.setPreferredSize(new Dimension(1080,720));
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel registrationPanel = new RegistrationPanel();
        
        JPanel workshopPanel = new WorkshopPanel();

		tabbedPane.addTab("Register Members",registrationPanel);
		
		tabbedPane.addTab("Register for Workshops",workshopPanel);

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
    	System.out.println("run?");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
			public void run() {
            	System.out.println("run?");
                createAndShowGUI();
            }
        });
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
