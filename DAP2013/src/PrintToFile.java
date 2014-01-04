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

	public PrintToFile(){
		information = new ArrayList<String>();
	}
	public void setWriter(String t){
		if(t.equals("PARTICIPANT")){
			printFile = new File("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\PARTICIPANTS.txt"); //laptop
			//printFile = new File("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\PARTICIPANTS.txt"); //desktop
			try {
				fileWriter = new FileWriter(printFile,true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(t.equals("WKSHP_REGISTRATIONS")){
			printFile = new File("C:\\Users\\Young\\Documents\\GitHub\\DAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //laptop
			//printFile = new File("C:\\Users\\Young\\git\\FBLADAP2013\\DAP2013\\WKSHP_REGISTRATIONS.txt"); //desktop
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
		int count = ReadFromFile.getData().size()+1;
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
		ReadFromFile read = new ReadFromFile("WKSHP_REGISTRATIONS");
		ArrayList<String>registrations = read.getRegistrations();
		setWriter("WKSHP_REGISTRATIONS");
		for(int i = 0; i < registrations.size();i++){
			String wkshpID = "[" + w.getID() + "]";
			if(wkshpID.equals(registrations.get(i).substring(0,5))){
				registrations.set(i,registrations.get(i) + ",[" + p.getID() + "]");
			}
		}
		for(int j = 0; j < registrations.size();j++){
			System.out.println(registrations.get(j));
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
