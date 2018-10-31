import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Maze extends JPanel implements KeyListener, MouseListener
{
	JFrame frame;
	char[][] mazeArr;
	int x = 1, y = 1;
	int playerDirection = 0;
	boolean gameDone = false;
	int moveCount = 0;

	ArrayList<String> lines = new ArrayList<>();

	public Maze()
	{
		setBoard();
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,800);
		frame.setVisible(true);
		frame.addKeyListener(this);
		//this.addMouseListener(this);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,800);

		g.setColor(Color.WHITE);
		int mazeX = 0;
		int mazeY = 0;
		for (char[] arr : mazeArr) {
			for (char c : arr) {
				if (c == 'X')
					g.drawRect(mazeX*20, mazeY*20, 20, 20);
				mazeX++;
			}
			mazeX = 0;
			mazeY++;
		}


		g.setColor(Color.RED);
		g.drawRect(760, 160, 20, 20);

		//PLAYER
		g.setColor(Color.BLUE);
		g.drawRect(x*20, y*20, 20, 20);
		System.out.println("(" + x + ", " + y + ")");

		//EYE
		switch (playerDirection) {
			case 0:   g.drawRect((x*20)+13, (y*20)+8, 4, 4); break;
			case 90:  g.drawRect((x*20)+8, (y*20)+13, 4, 4); break;
			case 180: g.drawRect((x*20)+3, (y*20)+8, 4, 4);  break;
			case 270: g.drawRect((x*20)+8, (y*20)+3, 4, 4);  break;
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.drawString("Moves: " + moveCount, 50, 400);

		if (gameDone)
			g.drawString("You Win!", 50, 600);

	}
	public void setBoard()
	{
		mazeArr = new char[10][40];

		File name = new File("mazeDesign.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while((text=input.readLine()) != null) {
				lines.add(text);
			}
		}
		catch (IOException io)
		{
			System.err.println("File error");
		}

		for (int i = 0; i < lines.size(); i++) {
			mazeArr[i] = lines.get(i).toCharArray();
		}
		for (char[] arr : mazeArr) {
			for (char c : arr) {
				System.out.print(c);
			}
			System.out.println();
		}

		//setWalls();
	}

	public void setWalls()
	{
		//when you're ready for the 3D part
		//int[] c1X={150,550,450,250};
		//int[] c1Y={50,50,100,100};
		//Ceiling ceiling1=new Polygon(c1X,c1Y,4);  //needs to be set as a global!

	}

	public void move() {
		if (!gameDone) {
			boolean canMove = true;
			switch (playerDirection) {
				case 0:   canMove =! (mazeArr[y][x+1]=='X'); break;
				case 90:  canMove =! (mazeArr[y+1][x]=='X'); break;
				case 180: canMove =! (mazeArr[y][x-1]=='X'); break;
				case 270: canMove =! (mazeArr[y-1][x]=='X'); break;
			}
			if (canMove) {
				switch (playerDirection) {
					case 0:   x++; break;
					case 90:  y++; break;
					case 180: x--; break;
					case 270: y--; break;
				}
				moveCount++;
			}
		}
		if (x == 38 && y == 8)
			gameDone = true;
	}

	public void turn(String direction) {
		if (!gameDone) {
			if (direction.equals("right")) {
				if (playerDirection == 270)
					playerDirection = 0;
				else
					playerDirection += 90;
			}
			if (direction.equals("left")) {
				if (playerDirection == 0)
					playerDirection = 270;
				else
					playerDirection -= 90;
			}
		}
	}



	public void keyPressed(KeyEvent e)
	{
		if (!gameDone) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_W: move();        break;
				case KeyEvent.VK_A: turn("left");  break;
				case KeyEvent.VK_D: turn("right"); break;
			}
			repaint();
		}
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}
	public void mouseClicked(MouseEvent e)
	{
	}
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public static void main(String args[])
	{
		Maze app=new Maze();
	}
}
