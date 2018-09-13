import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.StringBuilder;

public class main {

	static int line = 0;
	static ArrayList<Integer> set1;
	static ArrayList<Integer> set2;
	static ArrayList<Integer> set3;
	static BufferedReader input;

	// Checks for intersection
	public static ArrayList<Integer> intersection(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> intersect = new ArrayList<Integer>();
		for (Integer i : list1) {
			if (list2.contains(i))
				intersect.add(i);
		}
		return intersect;
	}

	public static void reset() {
		set1 = new ArrayList<Integer>();
		set2 = new ArrayList<Integer>();
		set3 = new ArrayList<Integer>();
	}

	// Reads file and adds to list
	public static void addToList() {
		try {
			for (int i=line;i<line+3;i++) {
				String text;
				String output = "";
				if ((text=input.readLine())!=null) {
					ArrayList<String> setTemp = new ArrayList<String>(Arrays.asList(text.split(" ")));
					for (String s : setTemp) {
						switch(i%3) {
							case 0: set1.add(Integer.parseInt(s)); break;
							case 1: set2.add(Integer.parseInt(s)); break;
							case 2: set3.add(Integer.parseInt(s)); break;
						}
					}
				}
			}
		} catch (IOException io) {
			
		}
		line+=3;
	}

	public static String formatting() {
		ArrayList<Integer> tempList = intersection(intersection(set1, set2), intersection(set2, set3));
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Integer i : tempList) {
			sb.append(i);
			sb.append(", ");
		}
		if (tempList.size() > 0) {
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) {

		File name = new File("input.txt");
		int setNum = 0;

		try {
			input = new BufferedReader(new FileReader(name));
			BufferedReader lineCounter = new BufferedReader(new FileReader(name));
			int lines = 0;
			while (lineCounter.readLine()!=null)
				lines++;
			lineCounter.close();

			int setCount = 1;
			while(lines>0) {
				reset();
				addToList();
				System.out.println("Set "+setCount+" intersection = "+formatting());
				lines-=3;
				setCount++;
			}

		} catch (IOException io) {
			System.err.println();
		}
	}
}