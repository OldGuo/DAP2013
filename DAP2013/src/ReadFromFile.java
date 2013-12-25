import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

	private static ArrayList<Workshop>workshops;
	private static ArrayList<Participant>participants;
	private static String choice;

	public ReadFromFile(){
		workshops = new ArrayList<Workshop>();
		participants = new ArrayList<Participant>();
	}
	public static ArrayList<Object> getData(String s){
		String choice = s;
		ArrayList<Object> data = new ArrayList<Object>();
		Scanner input = null;

		if(choice.equals("WORKSHOPS")){
			input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WORKSHOPS.txt"); //desktop
			//input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WORKSHOPS.txt"); //laptop
		}
		if(choice.equals("PARTICIPANTS")){ //participants
			input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\PARTICIPANTS.txt"); //desktop
			//input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt"); //laptop
		}
		if(choice.equals("WKSHP_REGISTRATIONS")){ //participants
			input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //desktop
			//input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //laptop
		}
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
			if(choice.equals("WORKSHOPS")){
				data.add(new Workshop(info));
			}else{ //participants
				data.add(new Participant(info));
			}
		}
		return data;
	}
}
