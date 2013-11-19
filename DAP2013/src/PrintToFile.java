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
	private final ArrayList<String>information;

	public PrintToFile(String type, ArrayList<String>a){
		setWriter(type);
		information = a;
	}
	public void setWriter(String t){
		if(t.equals("PARTICIPANT")){
			//printFile = new File("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt"); //laptop
			printFile = new File("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\PARTICIPANTS.txt"); //desktop
			try {
				fileWriter = new FileWriter(printFile,true);
			} catch (IOException e) {
				System.out.println("sorry, can't open your file");
				e.printStackTrace();
			}
		}
		bufferedWriter = new BufferedWriter(fileWriter);
	}
	public void registerParticipant(){
		int count = ReadFromFile.getParticipants().size()+1;
		try {
			bufferedWriter.write("["+count+"],");
			String s = "";
			for(int i = 0; i < information.size();i++){
				s += "["+information.get(i)+"]";
				if(i != information.size()-1)
					s+=",";
			}
			bufferedWriter.write(s);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
