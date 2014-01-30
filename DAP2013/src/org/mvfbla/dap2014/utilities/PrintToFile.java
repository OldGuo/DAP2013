package org.mvfbla.dap2014.utilities;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.mvfbla.dap2014.base.Participant;
import org.mvfbla.dap2014.base.Workshop;

/**
 * @author Young
 * Prints information to different files
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
	/**
	 * @param t
	 * Sets which file to write to and which type of writer to use
	 */
	public void setWriter(String t){
		if(t.equals("PARTICIPANT")){
			printFile = new File("Files\\PARTICIPANTS.txt"); //desktop
			try {
				fileWriter = new FileWriter(printFile,true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(t.equals("WKSHP_REGISTRATIONS")){
			printFile = new File("Files\\WKSHP_REGISTRATIONS.txt"); //desktop
			try {
				fileWriter = new FileWriter(printFile,false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bufferedWriter = new BufferedWriter(fileWriter);
	}
	/**
	 * @param a
	 * Information to create the participant from
	 */
	public void registerParticipant(ArrayList<String>a){
		setWriter("PARTICIPANT");
		information = a;
		//ReadFromFile read = new ReadFromFile("PARTICIPANTS");
		int count = ReadFromFile.getData("PARTICIPANTS").size()+1;
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
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param w
	 * Workshop to register for
	 * @param p
	 * Participant to register for the workshop
	 */
	public void registerForWorkshop(Workshop w, Participant p){
		ArrayList<String>registrations = ReadFromFile.getRegistrationList();
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
