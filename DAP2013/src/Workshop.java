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
}
