import java.util.ArrayList;


public class Conference {
	private final String CODE;
	private final String LOCATION;
	private final String START_DATE;
	private final String END_DATE;
	
	public Conference(ArrayList<String>a){
		CODE = a.get(0);
		LOCATION = a.get(1);
		START_DATE = a.get(2);
		END_DATE = a.get(3);
	}
	public String getCode(){
		return CODE;
	}
	public String getLocation(){
		return LOCATION;
	}
	public String getStartDate(){
		return START_DATE;
	}
	public String getEndDate(){
		return END_DATE;
	}
}
