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
	private final String station;

	public PrintToFile(){
		station = "desktop";
		information = new ArrayList<String>();
	}
	public void setWriter(String t){
		if(t.equals("PARTICIPANT")){
			if(station.equals("desktop")){
				printFile = new File("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\PARTICIPANTS.txt"); //desktop
			}else{
				printFile = new File("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt"); //laptop
			}
			try {
				fileWriter = new FileWriter(printFile,true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(t.equals("WKSHP_REGISTRATIONS")){
			if(station.equals("desktop")){
				printFile = new File("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //desktop
			}else{
				printFile = new File("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //laptop
			}
			try {
				fileWriter = new FileWriter(printFile,false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bufferedWriter = new BufferedWriter(fileWriter);
	}
	public void registerParticipant(ArrayList<String>a){
		setWriter("PARTICIPANT");
		information = a;
		ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		int count = read.getData().size()+1;
		try {
			bufferedWriter.newLine();
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
		ReadFromFile read = new ReadFromFile("WKSHP_REGISTRATIONS");
		ArrayList<String>registrations = read.getRegistrationList();
		setWriter("WKSHP_REGISTRATIONS");
		for(int i = 0; i < registrations.size();i++){
			String wkshpID = "[" + w.getID() + "]";
			if(wkshpID.equals(registrations.get(i).substring(0,5))){
				registrations.set(i,registrations.get(i) + ",[" + p.getID() + "]");
			}
		}
		try {
			for(int j = 0; j < registrations.size();j++){
				bufferedWriter.write(registrations.get(j));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
