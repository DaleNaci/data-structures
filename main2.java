import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;
import java.math.BigInteger;

public class main2 {

	static File name = new File("input2.txt");
	static ArrayList<BigInteger> list = new ArrayList<BigInteger>();

	public static BigInteger lucas(BigInteger count) {
		BigInteger x = BigInteger.valueOf(2);
		BigInteger y = BigInteger.valueOf(1);
		BigInteger temp;
		BigInteger tempY;
		for (BigInteger i = BigInteger.ZERO; i.compareTo(count) < 0; i = i.add(BigInteger.ONE)) {
			temp = new BigInteger(y+"");
			tempY = new BigInteger(y+"");
			y = new BigInteger((tempY.add(x))+"");
			x = new BigInteger(temp+"");
		}
		return x;
	}

	public static void main(String[] args) {
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output = "";
			while((text=input.readLine())!=null) {
				list.add(new BigInteger(text));
			}
			for (BigInteger i : list) {
				System.out.println(lucas(i));
			}
		} catch (IOException io) {
			System.err.println("File does not exist.");
		}
	}
}