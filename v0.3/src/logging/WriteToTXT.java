package logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteToTXT {

	public static void writeToFile(String args) {
		try {
			writeToTxt(args);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("FILE WRITING ERROR 002: COULD NOT WRITE TO logs/latest.log");
		}
	}
	
	private static void writeToTxt(String args) throws IOException {
		
		FileWriter fw = null; BufferedWriter bw = null; PrintWriter pw = null;


		
		try {
			fw = new FileWriter("logs/latest.log", true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.println(getDateAndTime() + " :    " + args);
			pw.flush();
		}  catch (IOException io) { io.printStackTrace(); }
	}
	
	public static void clearLogFile() throws IOException {
		File f = new File("logs/latest.log");
		
		FileWriter fw = new FileWriter(f);
		
		@SuppressWarnings("resource")
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println("LOGGING STARTED AT: " + getDateAndTime() + "\n");
		
		pw.flush();
	}
	
	public static String getDateAndTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}
