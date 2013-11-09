import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RegistrationPanel extends JPanel{
	public RegistrationPanel(){
		this.setLayout(new GridLayout(8,2));
		JButton b = new JButton("SEND");
	    b.setToolTipText("click to send your information");

	    final JTextField first = new JTextField();
	    final JTextField last = new JTextField();
	    final JTextField chapter = new JTextField();
	    b.addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	    	System.out.println(first.getText() + " " + last.getText() + " " + chapter.getText());
	    	}
	    });
		String [] types = {"Member","Advisor","Guest"};
		String [] locations = {"DC","MN","LA"};
		JComboBox type = new JComboBox(types);
		JComboBox location = new JComboBox(locations);
	    this.add(new JLabel("Location:"));
	    this.add(location);
	    this.add(new JLabel("Type:"));
	    this.add(type);
		this.add(new JLabel("first name"));
		this.add(first);
		this.add(new JLabel("last name"));
		this.add(last);
		this.add(new JLabel("chapter"));
		this.add(chapter);
		this.add(b);

	}
}
