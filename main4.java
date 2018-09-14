import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Arrays;
import java.util.ArrayList;

public class main4 {

	static File name = new File("input4.txt");
	static ArrayList<String> lines = new ArrayList<String>();

	static int rows, columns, side, rowTop, colTop;

	public static void varSet(String text) {
		String[] arr = text.split(" ");
		rows = Integer.parseInt(arr[0]);	//6 8
		columns = Integer.parseInt(arr[1]);	//8 9
		side = Integer.parseInt(arr[2]);	//3 4
		rowTop = Integer.parseInt(arr[3]);	//0 1
		colTop = Integer.parseInt(arr[4]);	//3 3
	}

	public static void diamond() {
		for (int i=0;i<rowTop;i++) {
			printO(columns+1);
			System.out.println();
		}

		for (int i=0;i<side;i++) {
			printO(colTop-i);
			printX(i*2+1);
			printO(columns-colTop-i);
			System.out.println();
		}

		for (int i=side-1;i>0;i--) {
			printO(colTop-i+1);
			printX(i*2-1);
			printO(columns-colTop-i+1);
			System.out.println();
		}

		for (int i=0;i<(rows-(side*2-1)-rowTop);i++) {
			printO(columns+1);
			System.out.println();
		}

	}

	public static void printO(int n) {
		if (n > 0) {
			System.out.print("o");
			printO(n-1);
		}
	}

	public static void printX(int n) {
		if (n > 0) {
			System.out.print("x");
			printX(n-1);
		}
	}

	public static void main(String[] args) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output="";
			while ((text=input.readLine())!=null) {
				lines.add(text);
			}
			for (String s : lines) {
				varSet(s);
				diamond();
			}
		} catch (IOException io) {
			System.err.println("File not found.");
		}
	}
}