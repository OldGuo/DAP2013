import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Young
 * Reads from files to do various tasks
 */
public class ReadFromFile {

	private static Scanner input;
	private static String readType;
	private static String station;
	public ReadFromFile(String s){
		station = "desktop";
		readType = s;
		selectReader(readType);
	}
	/**
	 * @param s
	 * Sets the file to read from
	 */
	public static void selectReader(String s){
		readType = s;
		if(s.equals("WORKSHOPS")){
			if(station.equals("desktop")){
				input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WORKSHOPS.txt"); //desktop
			}else{
				input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WORKSHOPS.txt"); //laptop
			}
		}
		if(s.equals("PARTICIPANTS")){ //participants
			if(station.equals("desktop")){
				input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\PARTICIPANTS.txt"); //desktop
			}else{
				input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt"); //laptop
			}
		}
		if(s.equals("WKSHP_REGISTRATIONS")){
			if(station.equals("desktop")){
				input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //desktop
			}else{
				input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //laptop
			}
		}
		if(s.equals("CONFERENCES")){
			if(station.equals("desktop")){
				input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\CONFERENCES.txt"); //desktop
			}else{
				input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\CONFERENCES.txt"); //laptop
			}
		}
	}
	/**
	 * @param s
	 * The type of data to retrieve
	 * Also defines which file to read from
	 * @return
	 * An arraylist of objects of specified information
	 */
	public static ArrayList<Object> getData(String s){
		selectReader(s);
		ArrayList<Object> data = new ArrayList<Object>();

		ArrayList<String>info = new ArrayList<String>(); //holds all the information for a specific workshop line
		while(input.hasNextLine()){
			info = new ArrayList<String>(); //clears the arraylist after each new line
			String line = input.nextLine(); //the line of text to be converted into workshop information
			String temp = ""; //a temporary String to hold the next workshop component
			int count = 0;
			int i = 0;
			while(i+1<line.length()){
				temp = "";
				if(line.charAt(i) == '['){
					int j = i;
					while(line.charAt(j+1) != ']'){
						temp += line.charAt(j+1);
						j++;
					}
					info.add(count, temp);
					count++;
				}
				i++;
			}
			if(info.size() > 0){
				if(readType.equals("WORKSHOPS")){
					data.add(new Workshop(info));
				}else if(readType.equals("PARTICIPANTS")){ //participants
					data.add(new Participant(info));
				}else if(readType.equals("CONFERENCES")){
					data.add(new Conference(info));
				}
			}
		}
		return data;
	}
	/**
	 * @return
	 * The list of lines from WKSHP_REGISTRATIONS
	 */
	public static ArrayList<String> getRegistrationList(){
		selectReader("WKSHP_REGISTRATIONS");
		ArrayList<String>registrations = new ArrayList<String>();
		while(input.hasNextLine()){
			registrations.add(input.nextLine());
		}
		return registrations;
	}
	/**
	 * @param ID
	 * ID of the workshop to find
	 * @return
	 * The workshop found from the ID
	 */
	public static Workshop getWorkshopByID(String ID){
		ArrayList<Object>data = ReadFromFile.getData("WORKSHOPS");
		Workshop w = null;
		for(int i = 0; i < data.size();i++){
			Workshop temp = (Workshop)data.get(i);
			if(temp.getID().equals(ID)){
				w = temp;
			}
		}
		return w;
	}
	/**
	 * @param code
	 * Workshop conference code
	 * @param title
	 * Workshop title
	 * @param date
	 * Workshop date
	 * @param time
	 * Workshop time of day
	 * @return
	 * Workshop found from the specified information
	 */
	public static Workshop getWorkshopByInfo(String code, String title, String date, String time){
		ArrayList<Object>data = ReadFromFile.getData("WORKSHOPS");
		Workshop w = null;
		for(int i = 0; i < data.size();i++){
			Workshop temp = (Workshop)data.get(i);
			if(temp.getCode().equals(code) && temp.getTitle().equals(title) && temp.getDate().equals(date) && temp.getTime().equals(time)){
				w = temp;
			}
		}
		return w;
	}
	/**
	 * @param ID
	 * ID of the participant to locate
	 * @return
	 * Participant with matching ID
	 */
	public static Participant getParticipantByID(String ID){
		ArrayList<Object>data = ReadFromFile.getData("PARTICIPANTS");
		Participant p = null;
		for(int i = 0; i < data.size();i++){
			Participant temp = (Participant)data.get(i);
			if(temp.getID().equals(ID)){
				p = temp;
			}
		}
		return p;
	}
	/**
	 * @param type
	 * Type of participant
	 * @param first
	 * First name of the participant
	 * @param last
	 * Last name of the participant
	 * @param chapter
	 * Chapter of the participant
	 * @return
	 * Participant with matching information
	 */
	public static Participant getParticipantByInfo(String type, String first, String last, String chapter){
		ArrayList<Object>data = ReadFromFile.getData("PARTICIPANTS");
		Participant p = null;
		for(int i = 0; i < data.size();i++){
			Participant temp = (Participant)data.get(i);
			if(temp.getType().equals(type) && temp.getFirstName().equals(first) && temp.getLastName().equals(last) && temp.getChapter().equals(chapter)){
				p = temp;
			}
		}
		return p;
	}
}
