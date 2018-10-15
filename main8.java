import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;


public class main8 {

	static File name = new File("input8.txt");
	static ArrayList<String> lines = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output="";
			while ((text=input.readLine())!=null) {
				lines.add(text);
			}
			int tripCount = 1;
			for (String line : lines) {
				Date today = Calendar.getInstance().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm zzz");
				String currentTime = sdf.format(today);
				long epochCurrent = 0;
				try {
					Date date = sdf.parse(currentTime);
					epochCurrent = date.getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long epochFinal = epochCurrent;
				String[] arr = line.split(" ");
				epochFinal += (86400000 * Long.parseLong(arr[0])) + (3600000 * Long.parseLong(arr[1])) + (60000 * Long.parseLong(arr[2]));
				SimpleDateFormat time = new SimpleDateFormat("h:mm a");
				SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
				System.out.println("Trip "+tripCount+":");
				System.out.println("\t Departure Date and Time: "+time.format(new Date(epochCurrent))+" on "+date.format(new Date(epochCurrent)));
				System.out.println("\t Arrival Date and Time: "+time.format(new Date(epochFinal))+" on "+date.format(new Date(epochFinal)));
				tripCount++;
			}

		} catch (IOException io) {
			System.err.println("File not found.");
		}
	}
}
