import java.util.ArrayList;
import java.util.Scanner;


public class FileParser {
	private final ArrayList<Workshop>workshops;
	String[][]information;
	public FileParser(){
		workshops = new ArrayList<Workshop>();
	}
	public void getWorkhops(){
		Scanner input = OpenFile.open("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WORKSHOPS.txt");
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
		System.out.println(workshops);
	}
}
