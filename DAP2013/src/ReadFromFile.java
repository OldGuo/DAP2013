import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

	private static Scanner input;
	private static String readType;

	public ReadFromFile(String s){
		readType = s;
		selectReader(readType);
	}
	public static void selectReader(String s){
		if(s.equals("WORKSHOPS")){
			input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WORKSHOPS.txt"); //desktop
			//input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WORKSHOPS.txt"); //laptop
		}
		if(s.equals("PARTICIPANTS")){ //participants
			input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\PARTICIPANTS.txt"); //desktop
			//input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt"); //laptop
		}
		if(s.equals("WKSHP_REGISTRATIONS")){
			input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //desktop
			//input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //laptop
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
			if(readType.equals("WORKSHOPS")){
				data.add(new Workshop(info));
			}else{ //participants
				data.add(new Participant(info));
			}
		}
		return data;
	}
	public static ArrayList<String> getRegistrations(){
		ArrayList<String>registrations = new ArrayList<String>();
		while(input.hasNextLine()){
			registrations.add(input.nextLine());
		}
		System.out.println(registrations);
		return registrations;
	}
}
