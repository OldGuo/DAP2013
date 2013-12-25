import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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

	public PrintToFile(String type){
		setWriter(type);
		information = new ArrayList<String>();
	}
	public void setWriter(String t){
		if(t.equals("PARTICIPANT")){
			//printFile = new File("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt"); //laptop
			printFile = new File("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\PARTICIPANTS.txt"); //desktop
		}
		if(t.equals("WKSHP_REGISTRATIONS")){
			//printFile = new File("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //laptop
			printFile = new File("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //desktop
		}
		try {
			fileWriter = new FileWriter(printFile,true);
		} catch (IOException e) {
			System.out.println("sorry, can't open your file");
			e.printStackTrace();
		}
		bufferedWriter = new BufferedWriter(fileWriter);
	}
	public void registerParticipant(ArrayList<String>a){
		information = a;
		int count = ReadFromFile.getData("PARTICIPANTS").size()+1;
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
	public void registerForWorkshop(Workshop w, Participant p){
		BufferedReader reader = null;
		ArrayList<String>registrations = new ArrayList<String>();
		try {
			File file = new File("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt");
		    reader = new BufferedReader(new FileReader(file));

		    String line = reader.readLine();
		    while (line != null) {
		    	String workshopId = "["+w.getId()+"]";
		        if(line.substring(0,5).equals(workshopId)){
		        	System.out.println(w.getTitle());
		        	String replacement = line + ",["+p.getID()+"]";
		        	line = line.replaceAll(line, replacement);
		        }
		    	line = reader.readLine();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
}
