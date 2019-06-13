import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.util.*;

public class Main extends JPanel implements KeyListener, Runnable {
    // OBJECTS
    JFrame      frame;
    Thread      t;
    Hero        hero;
    Enemy       enemy;
    Rectangle   heroHitbox;

    // PRIMITIVES
    boolean gameOn;
    boolean restart;
    boolean left;
    boolean right;

    // ARRAYS & ARRAYLISTS
    BufferedImage[]         background;
    int[]                   backgroundX;
    int[]                   backgroundSpeed;
    ArrayList<Platform>     platforms;
    ArrayList<String>       lines;
    ArrayList<Rectangle>    platformHitboxes;

	public Main() {

        // OBJECTS
		frame       =   new JFrame();
        t           =   new Thread(this);
        hero        =   new Hero(80, 270);
        heroHitbox  =   new Rectangle(90, 340, 60, 10);


        // PRIMITIVES
		gameOn      =   true;
        restart     =   false;

        // ARRAYS
        background          =   new BufferedImage[8];
        backgroundX         =   new int[8];
        backgroundSpeed     =   new int[] {0, 0, 1, 1, 1, 1, 3, 3};
        platforms           =   new ArrayList<>();
        lines               =   new ArrayList<>();
        platformHitboxes    =   new ArrayList<>();

        try {
            for (int i = 0; i < 8; i++) {
                background[i] = ImageIO.read(new File("background/layer" + Integer.toString(8 - i) + ".png"));
            }
            enemy   =   new Enemy(ImageIO.read(new File("sprite/st1.png")).getSubimage(0, 81, 85, 85), 1300, 278);
        } catch (IOException e) { System.out.println("ERROR"); }


        try {
            BufferedReader input = new BufferedReader(new FileReader(new File("Level.txt")));
            String text = "";
            String output = "";
            while ((text = input.readLine()) != null) {
                lines.add(text);
            }
        } catch (IOException e) { System.out.println("ERROR"); }
        try {
            BufferedImage platformImage = ImageIO.read(new File("sprite/block.png"));
            int x = 0;
            int y = 10;
            for (String line : lines) {
                for (String c : line.split("")) {
                    if (c.equals("1")) {
                        platforms.add(new Platform(x, y, platformImage));
                        platformHitboxes.add(new Rectangle(x, y, 50, 5));
                    }
                    x += 42;
                }
                x = 0;
                y += 42;
            }
        } catch (IOException e) { System.out.println("ERROR"); }



        Arrays.fill(backgroundX, 0);

		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(800, 450);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t.start();
	}

	public void run() {
		while(true) {
			if(gameOn) {
                boolean intersecting = false;
                for (Rectangle rect : platformHitboxes) {
                    if (heroHitbox.intersects(rect))
                        intersecting = true;
                    if (heroHitbox.intersects(rect) && hero.getVelocity() > 0) {
                        // System.out.println("Rect: (" + rect.getX() + ", " + rect.getY() + ") (" + (rect.getX() + rect.getWidth()) + ", " + (rect.getY() + rect.getHeight()) + ")");
                        // System.out.println("Hero: (" + heroHitbox.getX() + ", " + heroHitbox.getY() + ") (" + (heroHitbox.getX() + heroHitbox.getWidth()) + ", " + (heroHitbox.getY() + heroHitbox.getHeight()) + ")");
                        hero.setVelocity(0);
                        hero.setJumping(false);
                    }
                }
                if (!intersecting && hero.getY() < 270) {
                    hero.setJumping(true);
                }

                hero.setFramecount(hero.getFramecount() + 1);
                hero.setY(hero.getY() + hero.getVelocity());
                if (hero.getY() > 270) {
                    hero.setJumping(false);
                    hero.setY(270);
                    hero.setVelocity(0);
                }
                if (hero.isJumping()) {
                    hero.setVelocity(hero.getVelocity() + 1);
                }
                if (right) {
                    for (int i = 0; i < 8; i++) {
                        backgroundX[i] -= backgroundSpeed[i];
                    }
                    for (Platform pf : platforms) {
                        pf.setX(pf.getX() - 3);
                    }
                    for (Rectangle rect : platformHitboxes) {
                        rect.setLocation((int)(rect.getX() - 3), (int)rect.getY());
                    }
                    enemy.setX(enemy.getX() - 3);
                    if (!hero.getCurrentAction().equals("run")) {
                        hero.setCurrentAction("run");
                        hero.setImageIndex(0);
                    }
                }
                if (left) {
                    for (int i = 0; i < 8; i++) {
                        backgroundX[i] += backgroundSpeed[i];
                    }
                    for (Platform pf : platforms) {
                        pf.setX(pf.getX() + 3);
                    }
                    for (Rectangle rect : platformHitboxes) {
                        rect.setLocation((int)(rect.getX() + 3), (int)rect.getY());
                    }
                    enemy.setX(enemy.getX() + 3);
                    if (!hero.getCurrentAction().equals("run")) {
                        hero.setCurrentAction("run");
                        hero.setImageIndex(0);
                    }
                }
                heroHitbox = new Rectangle(hero.getX() + 10, hero.getY() + 70, 60, 10);
                for (int i = 0; i < 8; i++) {
                    if (backgroundX[i] < -800)
                        backgroundX[i] += 800;
                }
                if (hero.isJumping()) {
                    hero.setCurrentAction("jump");
                    if (right == left) {
                        hero.setDirection("right");
                    } else if (right) {
                        hero.setDirection("right");
                    } else if (left) {
                        hero.setDirection("left");
                    }
                } else if (right == left) {
                    hero.setCurrentAction("stand");
                    hero.setDirection("right");
                } else if (right) {
                    hero.setDirection("right");
                } else if (left) {
                    hero.setDirection("left");
                }
                if (hero.getCurrentAction().equals("stand")) {
                    if (hero.getImageIndex() >= 2)
                        hero.setImageIndex(0);
                    hero.setImage(hero.getImageIndex());
                    if (hero.getFramecount() > 30) {
                        hero.setImageIndex(hero.getImageIndex() + 1);
                        hero.setFramecount(0);
                    }
                }
                if (hero.getCurrentAction().equals("run")) {
                    if (hero.getImageIndex() >= 5)
                        hero.setImageIndex(0);
                    hero.setImage(hero.getImageIndex());
                    if (hero.getFramecount() > 10) {
                        hero.setImageIndex(hero.getImageIndex() + 1);
                        hero.setFramecount(0);
                    }
                }

                if (enemy.getDirection().equals("left"))
                    enemy.setRelativeX(enemy.getRelativeX() - 2);
                if (enemy.getDirection().equals("right"))
                    enemy.setRelativeX(enemy.getRelativeX() + 2);
                if (enemy.getRelativeX() < -160)
                    enemy.setDirection("right");
                if (enemy.getRelativeX() > 160)
                    enemy.setDirection("left");

				repaint();
			}
			if(restart) {
				restart = false;
				gameOn = true;
			}
			try {
				t.sleep(10);
			} catch(InterruptedException e) { }
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		for (int i = 0; i < 8; i++) {
            g2d.drawImage(background[i], backgroundX[i], 0, null);
            g2d.drawImage(background[i], backgroundX[i] + background[i].getWidth(), 0, null);
            g2d.drawImage(background[i], backgroundX[i] - background[i].getWidth(), 0, null);
        }
        // if (hero.getDirection().equals("right")) {
        g2d.drawImage(hero.getCurrentImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT), hero.getX(), hero.getY(), null);
        // } else {
        //     // FACE OPPOSITE DIRECTION
        // }
        for (Platform pf : platforms) {
            g2d.drawImage(pf.getImage(), pf.getX(), pf.getY(), null);
        }

        g2d.drawImage(enemy.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT), enemy.getX() + enemy.getRelativeX(), enemy.getY(), null);

        g2d.setColor(Color.BLACK);

        g2d.drawRect((int)heroHitbox.getX() + 10, ((int)heroHitbox.getY()), 60, 10);

        for (Rectangle r : platformHitboxes) {
            g2d.drawRect((int)r.getX(), (int)r.getY(), 50, 5);
        }


	}
	public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == 37) {   // LEFT
            right = false;
            left  = true;
        }
        if(key.getKeyCode() == 39) {    // RIGHT
            right = true;
            left  = false;
        }
        if (key.getKeyCode() == 38) {   // UP
            if (!hero.isJumping())
                hero.setVelocity(-18);
            hero.setJumping(true);
        }
	}
	public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == 37) // LEFT
            left  = false;
        if (key.getKeyCode() == 39) // RIGHT
            right = false;
    }

	public void keyTyped(KeyEvent key) { }

	public static void main(String args[]) {
		Main app = new Main();
	}
}
