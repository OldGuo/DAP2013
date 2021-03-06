package org.mvfbla.dap2014.panels;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import org.mvfbla.dap2014.utilities.PrintToFile;


/**
 * @author Young
 * Panel with fields for users to register new participants
 */
public class RegistrationPanel extends JPanel implements ActionListener{
	private JComboBox type;
	private JComboBox location;
	private JButton send;
	private JTextField first, last, chapter;
	private JLabel conferenceLocation, participantType,firstName, lastName, chapterNumber;
	private ArrayList<String> information;
	private JFrame frame;

	private GridBagConstraints c;
	
	public RegistrationPanel(){
		this.frame = frame;
		//this.setLayout(new GridLayout(6,2,200,25));
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		createComponents();
	}
	/**
	 * Creates the components to allow the user to register new participants
	 */
	public void createComponents(){
		c.ipady = 0; 
		c.ipadx = 0; 
		c.insets = new Insets(15,50,0,50);  //top padding
		c.fill = GridBagConstraints.CENTER; 
		
		c.anchor = GridBagConstraints.PAGE_START; c.gridx = 0; c.gridwidth = 2; c.gridy = 0;
		JLabel title = new JLabel("Register Participants for Conferences");
		title.setFont(new Font("Arial",Font.PLAIN, 24));
		this.add(title, c);
		
		c.gridwidth = 1;
		c.ipady = 50; 
		c.fill = GridBagConstraints.HORIZONTAL; 
		
		send = new JButton("Register");
	    send.setToolTipText("click to send your information");
	    send.addActionListener(this);

	    first = new JTextField();
	    last = new JTextField();
	    chapter = new JTextField();
	    
	    firstName = new JLabel("Participant First Name:");
	    firstName.setFont(new Font("Arial",Font.PLAIN, 18));
	    
	    lastName = new JLabel("Participant Last Name:");
	   	lastName.setFont(new Font("Arial",Font.PLAIN, 18));
	    
	    chapterNumber = new JLabel("FBLA Chapter:");
	    chapterNumber.setFont(new Font("Arial",Font.PLAIN, 18));
	    
	    participantType = new JLabel("Participant Type:");
	    participantType.setFont(new Font("Arial",Font.PLAIN, 18));
	    
	    conferenceLocation = new JLabel("Conference Location:");
	    conferenceLocation.setFont(new Font("Arial",Font.PLAIN, 18));

		String [] types = {"Member","Advisor","Guest"};
		String [] locations = {"(11/7/14 - 11/8/14) Washington, DC",
				"(11/14/14 - 11/15/14) Minneapolis, MN",
				"(11/21/14 - 11/22/14) New Orleans, LA"};
		
		type = new JComboBox(types);
		type.addActionListener(this);
		location = new JComboBox(locations);
		location.addActionListener(this);
	    
		addComponents();
	}
	public void addComponents(){
		c.gridx = 0; c.gridy = 1; c.weightx = 1;
		this.add(conferenceLocation, c);
		c.gridx = 1; c.gridy = 1; c.weightx = 3;
	    this.add(location, c);
	    
	    c.gridx = 0; c.gridy = 2; c.weightx = 1;
	    this.add(participantType, c);
	    c.gridx = 1; c.gridy = 2; c.weightx = 3;
	    this.add(type, c);
		
	    c.gridx = 0; c.gridy = 3; c.weightx = 1;
	    this.add(firstName, c);
	    c.gridx = 1; c.gridy = 3; c.weightx = 3;
		this.add(first, c);
		
		c.gridx = 0; c.gridy = 4; c.weightx = 1;
		this.add(lastName, c);
		c.gridx = 1; c.gridy = 4; c.weightx = 3;
		this.add(last, c);
		
		c.gridx = 0; c.gridy = 5; c.weightx = 1;
		this.add(chapterNumber, c);
		c.gridx = 1; c.gridy = 5; c.weightx = 3;
		this.add(chapter, c);
		
		c.anchor = GridBagConstraints.PAGE_END; c.gridx = 0; c.gridwidth = 2; c.gridy = 6;
		this.add(send, c);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == send){
			String tempLoc = (String)location.getSelectedItem();
			String loc = tempLoc.substring(tempLoc.length() - 2, tempLoc.length());
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
