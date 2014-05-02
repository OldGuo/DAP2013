package org.mvfbla.dap2014.base;
/*
 * Workshop class
 * 	Contains 6 fields to aid participants in pre-registering for workshops:
 * 		ID: Unique Identification number classifying the workshop
 * 		CODE: The conference code of the workshop
 * 		TITLE: The workshop title
 * 		DESCRIPTION: Brief description of the workshop
 * 		DATE: Date specifying which one of two days the workshop is held
 * 		TIME: The time of day of the workshop
 */
import java.util.ArrayList;

/**
 * @author Young
 * Workshop class defined by an id, code, title, description, date, time, 
 */
public class Workshop {

	private final String ID;
	private final String CODE;
	private final String TITLE;
	private final String DESCRIPTION;
	private final String DATE;
	private final String TIME;
	private final ArrayList<String>participants;
	/**
	 * @param a
	 * Arraylist of information to be converted into id, code, title, description, date, and time for the workshop
	 */
	public Workshop(ArrayList<String>a){
		ID = a.get(0);
		CODE = a.get(1);
		TITLE = a.get(2);
		DESCRIPTION = a.get(3);
		DATE = a.get(4);
		TIME = a.get(5);
		participants = new ArrayList<String>();
	}
	/**
	 * @return
	 * specific ID of the workshop
	 */
	public String getID(){
		return ID;
	}
	/**
	 * @return
	 * Conference code of the workshop
	 */
	public String getCode(){
		return CODE;
	}
	/**
	 * @return
	 * Title of the workshop
	 */
	public String getTitle(){
		return TITLE;
	}
	/**
	 * @return
	 * Description of the workshop
	 */
	public String getDescrip(){
		return DESCRIPTION;
	}
	/**
	 * @return
	 * Date of the workshop
	 */
	public String getDate(){
		return DATE;
	}
	/**
	 * @return
	 * Time of day of the workshop
	 */
	public String getTime(){
		return TIME;
	}
	public void register(String participantID){
		     participants.add(participantID);
		   }
	public ArrayList<String> getParticipants(){
		     return participants;
		   }
	
}
