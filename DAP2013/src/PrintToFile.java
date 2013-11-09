import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Prints information to files
 * 	participant information\
 * 	workshop preregistration information
 */

public class PrintToFile {
	
	private File printFile;
	private BufferedWriter bufferedWriter;
	private FileWriter fileWriter;
	private ArrayList<String>information;
	
	public PrintToFile(String type, ArrayList<String>a){
		setWriter(type);	
		information = a;
	}
	public void setWriter(String t){
		if(t.equals("PARTICIPANT")){
			printFile = new File("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt");
			try {
				fileWriter = new FileWriter(printFile,true);
			} catch (IOException e) {
				System.out.println("sorry, can't open your file");
				e.printStackTrace();
			}
		}
		bufferedWriter = new BufferedWriter(fileWriter);
	}
	public void printInfo(){
		try {
			String s = "";
			for(int i = 0; i < information.size();i++){
				s += "["+information.get(i)+"]";
			}
			bufferedWriter.write(s);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
