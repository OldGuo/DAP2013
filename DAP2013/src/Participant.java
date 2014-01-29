import java.util.ArrayList;

/**
 * @author Young
 * Participant class defined by an id, code, type, first name, last name, and chapter
 */
public class Participant {

	private final String ID;
	private final String CODE;
	private final String TYPE;
	private final String FIRST;
	private final String LAST;
	private final String CHAPTER;

	/**
	 * @param a
	 * Converts arraylist of information into defining id, code, type, first name, last name, and chapter
	 */
	public Participant(ArrayList<String>a){
		ID = a.get(0);
		CODE = a.get(1);
		TYPE = a.get(2);
		FIRST= a.get(3);
		LAST = a.get(4);
		CHAPTER = a.get(5);
	}
	/**
	 * @return
	 * specific ID for the participant
	 */
	public String getID(){
		return ID;
	}
	/**
	 * @return
	 * Conference code
	 */
	public String getCode(){
		return CODE;
	}
	/**
	 * @return
	 * Type of the participant (Member, adviser, or guest)
	 */
	public String getType(){
		return TYPE;
	}
	/**
	 * @return
	 * First name of the participant
	 */
	public String getFirstName(){
		return FIRST;
	}
	/**
	 * @return
	 * Last name of the participant
	 */
	public String getLastName(){
		return LAST;
	}
	/**
	 * @return
	 * Chapter code of the participant
	 */
	public String getChapter(){
		return CHAPTER;
	}

}
