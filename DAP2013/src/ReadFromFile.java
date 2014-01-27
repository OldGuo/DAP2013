import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

	private static Scanner input;
	private static String readType;
	private static String station;
	public ReadFromFile(String s){
		station = "desktop";
		readType = s;
		selectReader(readType);
	}
	public static void selectReader(String s){
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
	public static ArrayList<Object> getData(){
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
	public static ArrayList<String> getRegistrationList(){
		ArrayList<String>registrations = new ArrayList<String>();
		while(input.hasNextLine()){
			registrations.add(input.nextLine());
		}
		return registrations;
	}
	public static Workshop getWorkshop(String code, String title, String date, String time){
		ReadFromFile read = new ReadFromFile("WORKSHOPS");
		ArrayList<Object>data = read.getData();
		Workshop w = null;
		for(int i = 0; i < data.size();i++){
			Workshop temp = (Workshop)data.get(i);
			if(temp.getCode().equals(code) && temp.getTitle().equals(title) && temp.getDate().equals(date) && temp.getTime().equals(time)){
				w = temp;
			}
		}
		return w;
	}
	public static Participant getParticipant(String type, String first, String last, String chapter){
		ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		ArrayList<Object>data = read.getData();
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
