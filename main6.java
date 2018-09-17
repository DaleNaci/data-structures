import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Arrays;
import java.util.ArrayList;

public class main6 {

	static File name = new File("input6.txt");
	static ArrayList<String> lines = new ArrayList<String>();

	static int leap, start, month, day;
	static int totalDays;

	public static void varSet(String text) {
		String[] arr = text.split(" ");
		leap = Integer.parseInt(arr[0]);
		start = Integer.parseInt(arr[1]);
		month = Integer.parseInt(arr[2]);
		day = Integer.parseInt(arr[3]);
	}

	public static void addMonth() {
		totalDays = start;
		if (month>1) totalDays+=31;
		if (month>2) totalDays+=28;
		if (month>3) totalDays+=31;
		if (month>4) totalDays+=30;
		if (month>5) totalDays+=31;
		if (month>6) totalDays+=30;
		if (month>7) totalDays+=31;
		if (month>8) totalDays+=31;
		if (month>9) totalDays+=30;
		if (month>10) totalDays+=31;
		if (month>11) totalDays+=30;
		if (leap==1 && month>2) totalDays++;
	}

	public static void addDays() {
		totalDays+=day-1;
	}

	public static String dayName() {
		String name = "";
		switch (totalDays%7) {
			case 1: name = "Sunday"; break;
			case 2: name = "Monday"; break;
			case 3: name = "Tuesday"; break;
			case 4: name = "Wednesday"; break;
			case 5: name = "Thursday"; break;
			case 6: name = "Friday"; break;
			case 0: name = "Saturday"; break;
		}
		return name;
	}

	
	public static void main(String[] args) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output="";
			while ((text=input.readLine())!=null) {
				lines.add(text);
			}
			for (String line : lines) {
				varSet(line);
				addMonth();
				addDays();
				System.out.println(line+" "+dayName());
			}
		} catch (IOException io) {
			System.err.println("File not found.");
		}	
	}
}