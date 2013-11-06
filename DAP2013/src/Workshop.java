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

public class Workshop {

	private final String ID;
	private final String CODE;
	private final String TITLE;
	private final String DESCRIPTION;
	private final String DATE;
	private final String TIME;

	public Workshop(ArrayList<String>a){
		ID = a.get(0);
		CODE = a.get(1);
		TITLE = a.get(2);
		DESCRIPTION = a.get(3);
		DATE = a.get(4);
		TIME = a.get(5);
	}
	public String getId(){
		return ID;
	}
	public String getCode(){
		return CODE;
	}
	public String getTitle(){
		return TITLE;
	}
	public String getDescrip(){
		return DESCRIPTION;
	}
	public String getDate(){
		return DATE;
	}
	public String getTime(){
		return TIME;
	}
	/*@Override
	public String toString(){
		return ID + " " + CODE + " " + TITLE + " " + DESCRIPTION + " " + DATE + " " + DATE + " " + TIME + "\n";
	}*/
}
