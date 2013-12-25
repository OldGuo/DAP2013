//Panel where participants can register
//Input: Location, Type (Member, Adviser, Guest), First Name, Last Name, and Chapter

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RegistrationPanel extends JPanel implements ActionListener{
	private final JComboBox type;
	private final JComboBox location;
	private final JButton send;
	private final JTextField first,last,chapter;
	private ArrayList<String> information;

	public RegistrationPanel(){
		this.setLayout(new GridLayout(8,2));
		information = new ArrayList<String>();

		send = new JButton("SEND");
	    send.setToolTipText("click to send your information");
	    send.addActionListener(this);

	    first = new JTextField();
	    last = new JTextField();
	    chapter = new JTextField();

		String [] types = {"Member","Advisor","Guest"};
		String [] locations = {"DC","MN","LA"};
		type = new JComboBox(types);
		type.addActionListener(this);
		location = new JComboBox(locations);
		location.addActionListener(this);
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
		this.add(send);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == send){
			information = new ArrayList<String>();
			Collections.addAll(information,(String)location.getSelectedItem(),(String)type.getSelectedItem(),first.getText(),last.getText(),chapter.getText());

			first.setText("");
			last.setText("");
			chapter.setText("");
			PrintToFile print = new PrintToFile("PARTICIPANT");
			print.registerParticipant(information);
		}
	}
}
