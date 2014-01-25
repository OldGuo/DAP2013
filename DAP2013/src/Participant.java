import java.util.ArrayList;

public class Participant {

	private final String ID;
	private final String CODE;
	private final String TYPE;
	private final String FIRST;
	private final String LAST;
	private final String CHAPTER;

	public Participant(ArrayList<String>a){
		ID = a.get(0);
		CODE = a.get(1);
		TYPE = a.get(2);
		FIRST= a.get(3);
		LAST = a.get(4);
		CHAPTER = a.get(5);
	}
	public String getID(){
		return ID;
	}
	public String getCode(){
		//Conference code
		return CODE;
	}
	public String getType(){
		return TYPE;
	}
	public String getFirstName(){
		return FIRST;
	}
	public String getLastName(){
		return LAST;
	}
	public String getChapter(){
		return CHAPTER;
	}

}
