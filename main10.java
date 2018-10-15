import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;
import java.util.Arrays;

public class main10 {

	static File name = new File("input10.txt");
	static ArrayList<String> lines = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output = "";
			while ((text = input.readLine()) != null) {
				lines.add(text);
			}
		} catch (IOException io) {
			System.err.println("File not found.");
		}
		int measureTotal = lines.get(0).length() - lines.get(0).replace(",","").length() + 1;
		String[][] output = new String[30][measureTotal+1];
		for (String[] arr : output) {
			Arrays.fill(arr, "");
		}
		for (int i = 0; i <= measureTotal; i++) {
			output[0][i] = Integer.toString(i);
		}

		// Create table diagram for output
		output[0][0] = "Measure";
		output[1][0] = "G#";
		output[2][0] = "G";
		output[3][0] = "F#";
		output[4][0] = "F";
		output[5][0] = "E";
		output[6][0] = "D#";
		output[7][0] = "D";
		output[8][0] = "C#";
		output[9][0] = "C";
		output[10][0] = "B";
		output[11][0] = "A#";
		output[12][0] = "A";
		output[13][0] = "G#";
		output[14][0] = "G";
		output[15][0] = "F#";
		output[16][0] = "F";
		output[17][0] = "E";
		output[18][0] = "D#";
		output[19][0] = "D";
		output[20][0] = "C#";
		output[21][0] = "C";
		output[22][0] = "B";
		output[23][0] = "A#";
		output[24][0] = "A";
		output[25][0] = "G#";
		output[26][0] = "G";
		output[27][0] = "F#";
		output[28][0] = "F";
		output[29][0] = "E";

		String[][] sets = new String[measureTotal][5];

		for (int i = 0; i < lines.size(); i++) {
			String[] lineSplit = lines.get(i).split(",");
			for (int j = 0; j < lineSplit.length; j++) {
				sets[j][i] = lineSplit[j];
			}
		}

		// Set up output notes
		int currentMeasure = 1;
		for (String[] chord : sets) {
			char[] frets1 = chord[0].toCharArray();
			if (frets1[0] == 'o') output[29][currentMeasure] = "o";
			if (frets1[1] == 'o') output[24][currentMeasure] = "o";
			if (frets1[2] == 'o') output[19][currentMeasure] = "o";
			if (frets1[3] == 'o') output[14][currentMeasure] = "o";
			if (frets1[4] == 'o') output[10][currentMeasure] = "o";
			if (frets1[5] == 'o') output[5][currentMeasure] = "o";
			char[] frets2 = chord[1].toCharArray();
			if (frets2[0] == '*') output[28][currentMeasure] = "o";
			if (frets2[1] == '*') output[23][currentMeasure] = "o";
			if (frets2[2] == '*') output[18][currentMeasure] = "o";
			if (frets2[3] == '*') output[13][currentMeasure] = "o";
			if (frets2[4] == '*') output[9][currentMeasure] = "o";
			if (frets2[5] == '*') output[4][currentMeasure] = "o";
			char[] frets3 = chord[2].toCharArray();
			if (frets3[0] == '*') output[27][currentMeasure] = "o";
			if (frets3[1] == '*') output[22][currentMeasure] = "o";
			if (frets3[2] == '*') output[17][currentMeasure] = "o";
			if (frets3[3] == '*') output[12][currentMeasure] = "o";
			if (frets3[4] == '*') output[8][currentMeasure] = "o";
			if (frets3[5] == '*') output[3][currentMeasure] = "o";
			char[] frets4 = chord[3].toCharArray();
			if (frets4[0] == '*') output[26][currentMeasure] = "o";
			if (frets4[1] == '*') output[21][currentMeasure] = "o";
			if (frets4[2] == '*') output[16][currentMeasure] = "o";
			if (frets4[3] == '*') output[11][currentMeasure] = "o";
			if (frets4[4] == '*') output[7][currentMeasure] = "o";
			if (frets4[5] == '*') output[2][currentMeasure] = "o";
			char[] frets5 = chord[4].toCharArray();
			if (frets5[0] == '*') output[25][currentMeasure] = "o";
			if (frets5[1] == '*') output[20][currentMeasure] = "o";
			if (frets5[2] == '*') output[15][currentMeasure] = "o";
			if (frets5[3] == '*') output[10][currentMeasure] = "o";
			if (frets5[4] == '*') output[6][currentMeasure] = "o";
			if (frets5[5] == '*') output[1][currentMeasure] = "o";
			currentMeasure++;
		}

		// PRINT OUTPUT
		for (String[] arr : output) {
			for (String s : arr) {
				System.out.print(s + "\t");
			}
			System.out.println();
		}
	}
}