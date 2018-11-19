import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.shape.Polygon;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;

public class maze extends Application {

	AnimateObjects animate;
	GraphicsContext gc;
	Canvas canvas;
	Group root;
	Polygon outerLWall, outerRWall, middleLWall, middleRWall, innerLWall, innerRWall;
	Polygon midFWall, midM1Wall, midM2Wall, midBWall;
	Polygon left1Wall, left2Wall, left3Wall, right1Wall, right2Wall, right3Wall;

	static char[][] mazeArr;
	int playerDirection = 0;
	int x = 1, y = 1;
	boolean gameDone = false;
	int moveCount = 0;

	URL background;
	AudioClip background_clip;

	int framecount;
	Integer timeLeft;

	Text timer = new Text();
	boolean gameOver;

	ImageView winGIF = new ImageView();
	Image win;


	public class AnimateObjects extends AnimationTimer {
		public void handle(long now) {
			gc.clearRect(0, 0, 900, 800);
			if (framecount % 60 == 0 && timeLeft > 0 && !gameOver && !gameDone) {
				timeLeft--;
				timer.setText(timeLeft.toString());
			}
			timer.setX(50);
			timer.setY(30);
			framecount++;
			if (timeLeft < 1) {
				gameOver = true;
				timer.setText("GAME OVER!");
			}
			if (gameDone) {
				timer.setText("YOU WIN!");
				if(!root.getChildren().contains(winGIF))
					root.getChildren().add(winGIF);
				winGIF.setVisible(true);
			}
			if (gameDone || gameOver)
				background_clip.stop();
		}
	}



	public static void main(String[] args) {
		mazeArr = new char[10][40];
		File name = new File("mazeDesign.txt");
		ArrayList<String> lines = new ArrayList<>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while((text=input.readLine()) != null) {
				lines.add(text);
			}
		}
		catch (IOException io) {
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
		launch();
	}

	public void start(Stage stage) {
		stage.setTitle("3D Maze");
		root = new Group();
		canvas = new Canvas(900, 800);

		outerLWall = new Polygon();
		outerLWall.getPoints().addAll(new Double[] {
			0.0, 0.0,
			300.0, 250.0,
			300.0, 500.0,
			0.0, 750.0,
		});
		outerRWall = new Polygon();
		outerRWall.getPoints().addAll(new Double[] {
			900.0, 0.0,
			600.0, 250.0,
			600.0, 500.0,
			900.0, 750.0,
		});
		middleLWall = new Polygon();
		middleLWall.getPoints().addAll(new Double[] {
			301.0, 251.0,
			400.0, 333.0,
			400.0, 416.0,
			301.0, 499.0,
		});
		middleRWall = new Polygon();
		middleRWall.getPoints().addAll(new Double[] {
			599.0, 251.0,
			500.0, 333.0,
			500.0, 416.0,
			599.0, 499.0,
		});
		innerLWall = new Polygon();
		innerLWall.getPoints().addAll(new Double[] {
			401.0, 334.0,
			433.0, 360.0,
			433.0, 388.0,
			401.0, 415.0,
		});
		innerRWall = new Polygon();
		innerRWall.getPoints().addAll(new Double[] {
			499.0, 334.0,
			467.0, 360.0,
			467.0, 388.0,
			499.0, 415.0,
		});
		midM1Wall = new Polygon();
		midM1Wall.getPoints().addAll(new Double[] {
			301.0, 251.0,
			599.0, 251.0,
			599.0, 499.0,
			301.0, 499.0,
		});
		midM2Wall = new Polygon();
		midM2Wall.getPoints().addAll(new Double[] {
			401.0, 333.0,
			499.0, 333.0,
			499.0, 416.0,
			401.0, 416.0,
		});
		midBWall = new Polygon();
		midBWall.getPoints().addAll(new Double[] {
			434.0, 360.0,
			466.0, 360.0,
			466.0, 388.0,
			434.0, 388.0,
		});
		midFWall = new Polygon();
		midFWall.getPoints().addAll(new Double[] {
			900.0, 0.0,
			900.0, 900.0,
			0.0, 900.0,
			0.0, 0.0,
		});
		left1Wall = new Polygon();
		left1Wall.getPoints().addAll(new Double[] {
			0.0, 251.0,
			300.0, 251.0,
			300.0, 499.0,
			0.0, 499.0,
		});
		left2Wall = new Polygon();
		left2Wall.getPoints().addAll(new Double[] {
			300.0, 333.0,
			401.0, 333.0,
			401.0, 416.0,
			300.0, 416.0,
		});
		left3Wall = new Polygon();
		left3Wall.getPoints().addAll(new Double[] {
			400.0, 360.0,
			434.0, 360.0,
			434.0, 388.0,
			400.0, 388.0,
		});
		right1Wall = new Polygon();
		right1Wall.getPoints().addAll(new Double[] {
			900.0, 251.0,
			600.0, 251.0,
			600.0, 499.0,
			900.0, 499.0,
		});
		right2Wall = new Polygon();
		right2Wall.getPoints().addAll(new Double[] {
			600.0, 333.0,
			499.0, 333.0,
			499.0, 416.0,
			600.0, 416.0,
		});
		right3Wall = new Polygon();
		right3Wall.getPoints().addAll(new Double[] {
			500.0, 360.0,
			466.0, 360.0,
			466.0, 388.0,
			500.0, 388.0,
		});


		root.getChildren().add(canvas);
		Scene scene = new Scene(root, Color.rgb(55, 55, 55, 1.0));
		stage.setScene(scene);

		gc = canvas.getGraphicsContext2D();


		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				switch ((event).getCode()) {
					case W: move(); 	   break;
					case A: turn("left");  break;
					case D: turn("right"); break;
				}
			}
		});

		paintWalls();

		background = getClass().getResource("background.wav");
		background_clip = new AudioClip(background.toString());
		background_clip.setVolume(.15);
		background_clip.play();

		timeLeft = 60;

		timer.setText("60");
		timer.setX(50);
		timer.setY(30);
		timer.setFont(new Font(24));
		timer.setFill(Color.WHITE);

		gameOver = false;

		win = new Image("win.gif");
		winGIF = new ImageView();
		winGIF.setImage(win);
		winGIF.setX(270);
		winGIF.setY(265);

		animate = new AnimateObjects();
		animate.start();
		stage.show();
	}

	public void move() {
		if (!gameDone && !gameOver) {
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

		paintWalls();

		if (x == 38 && y == 8)
			gameDone = true;
	}

	public void turn(String direction) {
		if (!gameDone && !gameOver) {
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
		paintWalls();
	}

	public void paintWalls() {
		if (!gameDone && !gameOver)
			root.getChildren().clear();
		for (int i = 1; i < 4; i++) {
			try {
				if (playerDirection == 0) {
					if (mazeArr[y-1][x+i] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(outerLWall);  break;
							case 2: root.getChildren().add(middleLWall); break;
							case 3: root.getChildren().add(innerLWall);  break;
						}
					} else {
						switch(i) {
							case 1: root.getChildren().add(left1Wall); break;
							case 2: root.getChildren().add(left2Wall); break;
							case 3: root.getChildren().add(left3Wall); break;
						}
					}
					if (mazeArr[y+1][x+i] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(outerRWall);  break;
							case 2: root.getChildren().add(middleRWall); break;
							case 3: root.getChildren().add(innerRWall);  break;
						}
					} else {
						switch(i) {
							case 1: root.getChildren().add(right1Wall); break;
							case 2: root.getChildren().add(right2Wall); break;
							case 3: root.getChildren().add(right3Wall); break;
						}
					}
				}
				if (playerDirection == 90) {
					if (mazeArr[y+i][x+1] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(outerLWall);  break;
							case 2: root.getChildren().add(middleLWall); break;
							case 3: root.getChildren().add(innerLWall);  break;
						}
					} else {
						switch(i) {
							case 1: root.getChildren().add(left1Wall); break;
							case 2: root.getChildren().add(left2Wall); break;
							case 3: root.getChildren().add(left3Wall); break;
						}
					}
					if (mazeArr[y+i][x-1] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(outerRWall);  break;
							case 2: root.getChildren().add(middleRWall); break;
							case 3: root.getChildren().add(innerRWall);  break;
						}
					} else {
						switch(i) {
							case 1: root.getChildren().add(right1Wall); break;
							case 2: root.getChildren().add(right2Wall); break;
							case 3: root.getChildren().add(right3Wall); break;
						}
					}
				}
				if (playerDirection == 180) {
					if (mazeArr[y+1][x-i] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(outerLWall);  break;
							case 2: root.getChildren().add(middleLWall); break;
							case 3: root.getChildren().add(innerLWall);  break;
						}
					} else {
						switch(i) {
							case 1: root.getChildren().add(left1Wall); break;
							case 2: root.getChildren().add(left2Wall); break;
							case 3: root.getChildren().add(left3Wall); break;
						}
					}
					if (mazeArr[y-1][x-i] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(outerRWall);  break;
							case 2: root.getChildren().add(middleRWall); break;
							case 3: root.getChildren().add(innerRWall);  break;
						}
					} else {
						switch(i) {
							case 1: root.getChildren().add(right1Wall); break;
							case 2: root.getChildren().add(right2Wall); break;
							case 3: root.getChildren().add(right3Wall); break;
						}
					}
				}
				if (playerDirection == 270) {
					if (mazeArr[y-i][x-1] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(outerLWall);  break;
							case 2: root.getChildren().add(middleLWall); break;
							case 3: root.getChildren().add(innerLWall);  break;
						}
					} else {
						switch(i) {
							case 1: root.getChildren().add(left1Wall); break;
							case 2: root.getChildren().add(left2Wall); break;
							case 3: root.getChildren().add(left3Wall); break;
						}
					}
					if (mazeArr[y-i][x+1] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(outerRWall);  break;
							case 2: root.getChildren().add(middleRWall); break;
							case 3: root.getChildren().add(innerRWall);  break;
						}
					} else {
						switch(i) {
							case 1: root.getChildren().add(right1Wall); break;
							case 2: root.getChildren().add(right2Wall); break;
							case 3: root.getChildren().add(right3Wall); break;
						}
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) { }
		}
		for (int i = 1; i < 5; i++) {
			try {
				if (playerDirection == 0) {
					if (mazeArr[y][x+i] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(midFWall);  break;
							case 2: root.getChildren().add(midM1Wall); break;
							case 3: root.getChildren().add(midM2Wall); break;
							case 4: root.getChildren().add(midBWall);  break;
						}
					}
				}
				if (playerDirection == 90) {
					if (mazeArr[y+i][x] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(midFWall);  break;
							case 2: root.getChildren().add(midM1Wall); break;
							case 3: root.getChildren().add(midM2Wall); break;
							case 4: root.getChildren().add(midBWall);  break;
						}
					}
				}
				if (playerDirection == 180) {
					if (mazeArr[y][x-i] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(midFWall);  break;
							case 2: root.getChildren().add(midM1Wall); break;
							case 3: root.getChildren().add(midM2Wall); break;
							case 4: root.getChildren().add(midBWall);  break;
						}
					}
				}
				if (playerDirection == 270) {
					if (mazeArr[y-i][x] == 'X') {
						switch(i) {
							case 1: root.getChildren().add(midFWall);  break;
							case 2: root.getChildren().add(midM1Wall); break;
							case 3: root.getChildren().add(midM2Wall); break;
							case 4: root.getChildren().add(midBWall);  break;
						}
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) { }
		}
		root.getChildren().add(timer);
		if (!gameDone) {
			root.getChildren().add(winGIF);
			winGIF.setVisible(false);
		}
	}
}
