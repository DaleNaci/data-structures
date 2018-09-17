import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Arrays;
import java.util.ArrayList;

public class main5 {

	static File name = new File("input5.txt");
	static ArrayList<String> lines = new ArrayList<String>();

	static int[][] b;

	static int line = 0;
	static int t = 0;

	static String winner = "";

	public static void arrSetup() {
		for (int i=line;i<line+3;i++) {
			String[] arr = lines.get(i).split(" ");
			for (int j=0;j<3;j++) {
				if (arr[j].equals("X"))
					b[i%3][j] = 1;
			}
		}
	}

	public static void findWinner() {
		for (int x=0;x<3;x++)
			if (b[x][0]==b[x][1] && b[x][1]==b[x][2])
				winner = (b[x][0]==1) ? "X" : "O";
		for (int y=0;y<3;y++) 
			if (b[0][y]==b[1][y] && b[1][y]==b[2][y])
				winner = (b[0][y]==1) ? "X" : "O";
		if (b[0][0]==b[1][1] && b[1][1]==b[2][2])
			winner = (b[0][0]==1) ? "X" : "O";
		if (b[0][2]==b[1][1] && b[1][1]==b[2][0])
			winner = (b[2][0]==1) ? "X" : "O";
	}

	public static void main(String[] args) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output="";
			while ((text=input.readLine())!=null) {
				lines.add(text);
				t++;
			}
			for (int i=0;i<t/3;i++) {
				b = new int[3][3];
				winner = "-";
				arrSetup();
				findWinner();
				System.out.println(winner);
				line+=3;
			}
			for (int[] i : b) {
				for (int j : i) {
					System.out.print(j);
				}
				System.out.println();
			}
		} catch (IOException io) {
			System.err.println("File not found.");
		}	
	}
}