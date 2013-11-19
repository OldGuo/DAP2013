import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {
	private static ArrayList<Workshop>workshops;
	private static ArrayList<Participant>participants;
	String[][]information;

	public ReadFromFile(){
		workshops = new ArrayList<Workshop>();
		participants = new ArrayList<Participant>();
	}
	public static ArrayList<Workshop> getWorkshops(){
		workshops = new ArrayList<Workshop>();
		Scanner input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WORKSHOPS.txt"); //desktop
		//Scanner input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WORKSHOPS.txt"); //laptop
		ArrayList<String>info = new ArrayList<String>();
		while(input.hasNextLine()){
			info = new ArrayList<String>();
			String line = input.nextLine();
			int count = 0;
			String temp = "";
			int i = 0;
			while(i+1<line.length()){
				temp = "";
				if(line.charAt(i) == '[')
				{
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
			workshops.add(new Workshop(info));
		}
		return workshops;
	}
	public static ArrayList<Participant> getParticipants(){
		participants = new ArrayList<Participant>();
		Scanner input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\PARTICIPANTS.txt"); //desktop
		//Scanner input = OpenFile.open("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt"); //laptop
		ArrayList<String>info = new ArrayList<String>();
		while(input.hasNextLine()){
			info = new ArrayList<String>();
			String line = input.nextLine();
			int count = 0;
			String temp = "";
			int i = 0;
			while(i+1<line.length()){
				temp = "";
				if(line.charAt(i) == '[')
				{
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
			participants.add(new Participant(info));
		}
		return participants;
	}
}
