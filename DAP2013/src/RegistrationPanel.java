//Panel where participants can register
//Input: Location, Type (Member, Adviser, Guest), First Name, Last Name, and Chapter

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RegistrationPanel extends JPanel implements ActionListener{
	private JComboBox type;
	private JComboBox location;
	private JButton send;
	private JTextField first;
	private JTextField last;
	private JTextField chapter;
	private ArrayList<String> information;
	private JFrame frame;

	public RegistrationPanel(){
		this.frame = frame;
		this.setLayout(new GridLayout(6,2));
		information = new ArrayList<String>();
		createComponents();
	}
	public void createComponents(){
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
	    
		this.add(new JLabel("Conference Location:"));
	    this.add(location);
	    
	    this.add(new JLabel("Participant Type:"));
	    this.add(type);
		
	    this.add(new JLabel("Participant First Name:"));
		this.add(first);
		
		this.add(new JLabel("Participant Last Name:"));
		this.add(last);
		
		this.add(new JLabel("FBLA Chapter:"));
		this.add(chapter);
		
		this.add(send);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == send){
			String loc = (String)location.getSelectedItem();
			String participantType = (String)type.getSelectedItem();
			String firstName = first.getText();
			String lastName = last.getText();
			String chapterID = chapter.getText();
			
			if(firstName.length() > 0 && lastName.length() > 0 && chapterID.length() > 0){
				information = new ArrayList<String>();
				Collections.addAll(information,loc,participantType,firstName,lastName,chapterID);

				first.setText("");
				last.setText("");
				chapter.setText("");
				PrintToFile print = new PrintToFile();
				print.registerParticipant(information);
				JOptionPane.showMessageDialog(frame, firstName + " " + lastName + " successfully registered");
			}else{
				JOptionPane.showMessageDialog(frame, "Please fill out all registration areas","Fields Incomplete",JOptionPane.ERROR_MESSAGE);
				//create a dialog warning or something
			}
		}
	}
}
