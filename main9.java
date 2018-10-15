import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;
import java.util.Arrays;

public class main9 {

	static File name = new File("input9.txt");
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

		for (String line : lines) {
			int num = Integer.parseInt(line);
			char[][] arr = new char[num][num];
			// FILL ARRAY WITH '-'
			boolean first = true;
			int initialFillCounter = 0;

			for (char[] row : arr) {
				Arrays.fill(row, '-');
			}

			//For loop end calculation
			int loopEnd = 1;
			for (int i = 2; i <= num; i++) {
				loopEnd += i + (i%2);
			}

			if (num == 2) {
				arr[0][0] = '*';
				arr[0][1] = '*';
				arr[1][1] = '*';
			}
			else {

				// POINTERS
				int x = 0;
				int y = 0;

				// Direction counters
				int right = num;
				int down = 0;
				int left = 0;
				int up = 0;
				int rightC = 0, downC = 0, leftC = 0, upC = 0;

				for (int i = 0; i <= loopEnd; i++) {
					if (right > 0) {
						arr[x][y] = '*';
						right--;
						if (right == 0) {
							down = (downC==0) ? num-(2*downC) : num-(4*downC);
							rightC++;
						} else {
							y++;
						}
					}
					if (down > 0) {
						arr[x][y] = '*';
						down--;
						if (down == 0) {
							left = (downC==0) ? num-(2*leftC) : num-(4*leftC);
							downC++;
						} else {
							x++;
						}
					}
					if (left > 0) {
						arr[x][y] = '*';
						left--;
						if (left == 0) {
							up = (upC==0) ? num-2-(2*upC) : num-2-(4*upC);
							leftC++;
						} else {
							y--;
						}
					}
					if (up > 0) {
						arr[x][y] = '*';
						up--;
						if (up == 0) {
							right = num - (2*rightC);
							upC++;
						} else {
							x--;
						}
					}
				}
			}
			for (char[] row : arr) {
				for (char ch : row) {
					System.out.print(ch + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}