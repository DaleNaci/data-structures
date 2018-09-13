import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.Arrays;
import java.util.ArrayList;

public class main3 {

	static File name = new File("input3.txt");
	static ArrayList<String> lines = new ArrayList<String>();
	static ArrayList<Integer> numbers = new ArrayList<Integer>();
	static ArrayList<Integer> answer;

	public static void selectionSort() {
		int n = numbers.size();
		for (int i=0;i<n-1;i++) {
			int m = i;
			for(int j=i+1;j<n;j++)
				if(numbers.get(j)<numbers.get(m))
					m = j;
			int t = numbers.get(m);
			numbers.set(m,numbers.get(i));
			numbers.set(i,t);
		}
	}

	public static void convert(String text) {
		ArrayList<String> numberList = new ArrayList<String>(Arrays.asList(text.split(" ")));
		for (String s : numberList)
		{
			numbers.add(Integer.parseInt(s));
		}
		numbers.remove(0);
	}

	public static void jollySort() {
		answer = new ArrayList<Integer>();
		for (int i=0;i<numbers.size();i++) {
			if (i%2==0)
				answer.add(numbers.get(i/2));
			else
				answer.add(numbers.get(numbers.size()-1-(i/2)));
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
				numbers = new ArrayList<Integer>();
				convert(s);
				selectionSort();
				jollySort();
				System.out.println(answer);
			}

		} catch (IOException io) {
			System.err.println("File not found.");
		}

	}

}