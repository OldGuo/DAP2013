package org.mvfbla.dap2014.utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Young
 * Utility file to open files using the Scanner class
 */
public class OpenFile{
	/**
	 * @param fileString
	 * File path for the opening file
	 * @return
	 * returns the scanner
	 */
	public static Scanner open (String fileString){
		Scanner fromFile = null;
		File myFile = new File(fileString);
		try{
			fromFile = new Scanner(myFile);
		}catch(FileNotFoundException e){
			System.out.println("Sorry, can't open " + fileString);
			System.exit(1);
		}
		return fromFile;
	}
}