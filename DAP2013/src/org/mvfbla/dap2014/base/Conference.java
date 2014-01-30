package org.mvfbla.dap2014.base;
import java.util.ArrayList;

/**
 * @author Young
 * Conference class defined by a code, location, starting date, and ending date
 */
public class Conference {
	private final String CODE;
	private final String LOCATION;
	private final String START_DATE;
	private final String END_DATE;
	
	/**
	 * @param a
	 * Converts arraylist of information into defining code, location, start date, and end date
	 */
	public Conference(ArrayList<String>a){
		CODE = a.get(0);
		LOCATION = a.get(1);
		START_DATE = a.get(2);
		END_DATE = a.get(3);
	}
	/**
	 * @return Specific conference code of the conference
	 */
	public String getCode(){
		return CODE;
	}
	/**
	 * @return Location defining the location of the conference
	 */
	public String getLocation(){
		return LOCATION;
	}
	/**
	 * @return Starting date of the conference
	 */
	public String getStartDate(){
		return START_DATE;
	}
	/**
	 * @return Ending date of the conference
	 */
	public String getEndDate(){
		return END_DATE;
	}
}
