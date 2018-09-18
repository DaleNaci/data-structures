import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;

public class main7 {

	static File name = new File("input7.txt");
	static ArrayList<String> lines = new ArrayList<String>();

	static String num, limit;
	// NUM IS A STRING
	// LIMIT IS A STRING

	static int[] sums;
	static int lineNum;

	public static void varSet(String text) {
		String[] arr = text.split(" ");
		num = arr[0];
		limit = arr[1];
	}

	public static void splitting() {
		if (Integer.parseInt(num) < Integer.parseInt(limit) && num.length()==1) {
			sums = new int[1];
			sums[0] = Integer.parseInt(num);
		} else {
			if (num.length()==2) {
				// System.out.println("TWO");
				sums = new int[1];
				sums[0] = Integer.parseInt(num.substring(0,1))+Integer.parseInt(num.substring(1));
			}
			if (num.length()==3) {
				// System.out.println("THREE");
				sums = new int[3];
				sums[0] = Integer.parseInt(num.substring(0,1))+Integer.parseInt(num.substring(1));
				sums[1] = Integer.parseInt(num.substring(0,2))+Integer.parseInt(num.substring(2));
				sums[2] = Integer.parseInt(num.substring(0,1))+Integer.parseInt(num.substring(1,2))+Integer.parseInt(num.substring(2,3));
			}
			if (num.length()==4) {
				// System.out.println("FOUR"); 4721
				sums = new int[7];
				sums[0] = Integer.parseInt(num.substring(0,1))+Integer.parseInt(num.substring(1));
				sums[1] = Integer.parseInt(num.substring(0,2))+Integer.parseInt(num.substring(2));
				sums[2] = Integer.parseInt(num.substring(0,3))+Integer.parseInt(num.substring(3));
				sums[3] = Integer.parseInt(num.substring(0,1))+Integer.parseInt(num.substring(1,2))+Integer.parseInt(num.substring(2));
				sums[4] = Integer.parseInt(num.substring(0,1))+Integer.parseInt(num.substring(1,3))+Integer.parseInt(num.substring(3,4));
				sums[5] = Integer.parseInt(num.substring(0,2))+Integer.parseInt(num.substring(2,3))+Integer.parseInt(num.substring(3));
				sums[6] = Integer.parseInt(num.substring(0,1))+Integer.parseInt(num.substring(1,2))+Integer.parseInt(num.substring(2,3))+Integer.parseInt(num.substring(3,4));
			}
		}
	}

	public static int findAnswer() {
		int max = 0;
		for (int i=0;i<sums.length;i++) 
			if (sums[i] > max && !(sums[i] > Integer.parseInt(limit)))
				max = sums[i];
		return max;
	}

	
	public static void main(String[] args) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output="";
			while ((text=input.readLine())!=null) {
				lines.add(text);
			}
			lineNum = 1;
			for (String line : lines) {
				varSet(line);
				splitting();
				System.out.println("Output #"+lineNum+": "+findAnswer());
				lineNum++;
			}

		} catch (IOException io) {
			System.err.println("File not found.");
		}	
	}
}