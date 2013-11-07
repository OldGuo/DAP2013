import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OpenFile{
	public static Scanner open (String fileString){
		Scanner fromFile = null;
		File myFile = new File(fileString);
		try{
			fromFile = new Scanner(myFile);

		}catch(FileNotFoundException e){
			System.out.println("Sorry, can't open your file");
			System.exit(1);
		}
		return fromFile;
	}
}