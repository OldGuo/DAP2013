package org.mvfbla.dap2014.panels;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Young
 * Panel showing instructions for a variety of subjects
 */
public class InstructionPanel extends JPanel{
	private int instructionType;
	public InstructionPanel(int t){
		instructionType = t;
		createInstructionImages();
	}
	/**
	 * Creates images depending on the type of instructions to be shown
	 */
	public void createInstructionImages(){
		/*
		 * type 1 = workshop
		 * type 2 = participants
		 * type 3 = reports
		 * */
		if(instructionType == 1){
			this.setLayout(new GridLayout(1,2));
			this.add(new JLabel(new ImageIcon("src\\Files\\workshopRegistrationInstructions.png")));
			this.add(new JLabel(new ImageIcon("src\\Files\\workshopDescriptionInstructions.png")));
		}else if(instructionType == 2){
			this.setLayout(new GridLayout(1,2));
			this.add(new JLabel(new ImageIcon("src\\Files\\participantRegistrationInstructions.png")));
			this.add(new JLabel(new ImageIcon("src\\Files\\participantRegistrationInstructions2.png")));
		}else if(instructionType == 3){
			this.setLayout(new GridLayout(1,3));
			this.add(new JLabel(new ImageIcon("src\\Files\\reportInstructions1.png")));
			this.add(new JLabel(new ImageIcon("src\\Files\\reportInstructions2.png")));
			this.add(new JLabel(new ImageIcon("src\\Files\\reportInstructions3.png")));
		}
	}
}
