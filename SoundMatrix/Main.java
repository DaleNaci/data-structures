import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;
import java.util.*;

// ADD-ONS
// 1) Dark Mode
// 2) Restart Button
// 3) Play/Pause Button
// 4) Note Text

public class Main extends JPanel implements Runnable, ActionListener {
	JPanel		panel;
	JPanel		northPanel;
	JPanel		buttonPanel;
	JFrame		frame;
	JMenuBar	menuBar;
	JMenu		songs;
	JMenu		user;
	JMenuItem	song1;
	JMenuItem	song2;
	JMenuItem	song3;
	JMenuItem   load;
	JMenuItem   save;
	JButton		playButton;
	JButton		addButton;
	JButton		removeButton;
	JButton		clearButton;
	JButton		randomButton;
	JButton		darkButton;
	JButton		restartButton;
	Thread		timing;

	JToggleButton[][]	button;
	AudioClip[] 		soundClip;
	boolean[][]			userSong;

	boolean	notStopped;
	boolean	paused;
	int		columns;
	boolean restarted;

	public Main() {
		try {
			panel			= new JPanel   (                  );
			northPanel		= new JPanel   (                  );
			buttonPanel		= new JPanel   (                  );
			frame			= new JFrame   (   "Sound Matrix" );
			menuBar			= new JMenuBar (                  );
			songs			= new JMenu    (          "Songs" );
			user			= new JMenu    (           "User" );
			song1			= new JMenuItem(       "Birthday" );
			song2			= new JMenuItem( "Hot Cross Buns" );
			song3			= new JMenuItem(   "Jingle Bells" );
			load			= new JMenuItem(           "Load" );
			save			= new JMenuItem(           "Save" );
			playButton		= new JButton  (           "Play" );
			addButton		= new JButton  (     "Add Column" );
			removeButton	= new JButton  (  "Remove Column" );
			clearButton		= new JButton  (          "Clear" );
			randomButton	= new JButton  (         "Random" );
			darkButton		= new JButton  (           "Dark" );
			restartButton	= new JButton  (        "Restart" );
			timing			= new Thread   (             this );

			button		= new JToggleButton[8][12];
			soundClip 	= new AudioClip[8];
			userSong	= new boolean[8][12];

			notStopped	= true;
			paused		= true;
			columns		= 12;
			restarted	= false;

            URL sound1 = new URL( "file:C5.wav" );
			URL sound2 = new URL( "file:B4.wav" );
			URL sound3 = new URL( "file:A4.wav" );
			URL sound4 = new URL( "file:G4.wav" );
			URL sound5 = new URL( "file:F4.wav" );
			URL sound6 = new URL( "file:E4.wav" );
			URL sound7 = new URL( "file:D4.wav" );
			URL sound8 = new URL( "file:C4.wav" );

			soundClip[0] = JApplet.newAudioClip( sound1 );
			soundClip[1] = JApplet.newAudioClip( sound2 );
			soundClip[2] = JApplet.newAudioClip( sound3 );
			soundClip[3] = JApplet.newAudioClip( sound4 );
			soundClip[4] = JApplet.newAudioClip( sound5 );
			soundClip[5] = JApplet.newAudioClip( sound6 );
			soundClip[6] = JApplet.newAudioClip( sound7 );
			soundClip[7] = JApplet.newAudioClip( sound8 );

		} catch(MalformedURLException e) {
			System.out.println("File not found");
		}

		buttonSetup();

		song1.addActionListener(this);
		song2.addActionListener(this);
		song3.addActionListener(this);
		load.addActionListener(this);
		save.addActionListener(this);

		playButton.addActionListener(this);
		addButton.addActionListener(this);
		removeButton.addActionListener(this);
		clearButton.addActionListener(this);
		randomButton.addActionListener(this);
		darkButton.addActionListener(this);
		restartButton.addActionListener(this);

		songs.add(song1);
		songs.add(song2);
		songs.add(song3);
		menuBar.add(songs);

		user.add(load);
		user.add(save);
		menuBar.add(user);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(    playButton );
		buttonPanel.add(     addButton );
		buttonPanel.add(  removeButton );
		buttonPanel.add(   clearButton );
		buttonPanel.add(  randomButton );
		buttonPanel.add(    darkButton );
		buttonPanel.add( restartButton );
		buttonPanel.setLayout(new FlowLayout());
		northPanel.setLayout(new BorderLayout());
		northPanel.add(menuBar, BorderLayout.NORTH);
		northPanel.add(buttonPanel, BorderLayout.SOUTH);

		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(1000, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timing.start();
		timing.suspend();
	}

	public void run() {
		do {
			try {
				for (int i = 0; i < columns; i++) {
					for (int j = 0; j < 8; j++) {
						if (button[j][i].isSelected()) {
							soundClip[j].stop();
							soundClip[j].play();
							button[j][i].setBackground(Color.BLUE);
						}
					}
					new Thread().sleep(800);
					if (restarted) {
						i = 0;
						restarted = false;
						new Thread().sleep(400);
					}
				}
			} catch(InterruptedException e) { }
		} while(notStopped);
    }

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			if (paused) {
				playButton.setText("Pause");
				timing.resume();
			} else {
				playButton.setText("Play");
				timing.suspend();
			}
			paused = !paused;
		}
		if (e.getSource() == addButton) {
			if (columns < 21) {
				columns++;
				buttonSetup();
			}
		}
		if (e.getSource() == removeButton) {
			if (columns > 5) {
				columns--;
				buttonSetup();
			}
		}
		if (e.getSource() == clearButton) {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < 8; j++) {
					button[j][i].setSelected(false);
				}
			}
		}
		if (e.getSource() == randomButton) {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < 8; j++) {
					if (Math.random() > .5) {
						button[j][i].setSelected(true);
					} else {
						button[j][i].setSelected(false);
					}
				}
			}
		}
		if (e.getSource() == darkButton) {
			button[0][0].setBackground(Color.BLACK);
			if (darkButton.getText() == "Dark") {
				panel.setBackground(Color.BLACK);
				for (int i = 0; i < button.length; i++) {
					for (int j = 0; j < button[0].length; j++) {
						button[i][j].setBackground(Color.GRAY);
					}
				}
				darkButton.setText("Light");
			} else {
				panel.setBackground(Color.WHITE);
				for (int i = 0; i < button.length; i++) {
					for (int j = 0; j < button[0].length; j++) {
						button[i][j].setBackground(Color.WHITE);
					}
				}
				darkButton.setText("Dark");
			}
		}
		if (e.getSource() == restartButton) {
			restarted = true;
		}
		if (e.getSource() == save) {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < 8; j++) {
					userSong[j][i] = button[j][i].isSelected();
				}
			}
		}
		if (e.getSource() == load) {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < 8; j++) {
					button[j][i].setSelected(userSong[j][i]);
				}
			}
		}
		if (e.getSource() == song1) {
			columns = 25;
			buttonSetup();

			button[7][0].setSelected(true);
			button[7][1].setSelected(true);
			button[6][2].setSelected(true);
			button[7][3].setSelected(true);
			button[4][4].setSelected(true);
			button[5][5].setSelected(true);

			button[7][6].setSelected(true);
			button[7][7].setSelected(true);
			button[6][8].setSelected(true);
			button[7][9].setSelected(true);
			button[3][10].setSelected(true);
			button[4][11].setSelected(true);

			button[7][12].setSelected(true);
			button[7][13].setSelected(true);
			button[0][14].setSelected(true);
			button[2][15].setSelected(true);
			button[4][16].setSelected(true);
			button[5][17].setSelected(true);
			button[6][18].setSelected(true);

			button[1][19].setSelected(true);
			button[1][20].setSelected(true);
			button[2][21].setSelected(true);
			button[4][22].setSelected(true);
			button[3][23].setSelected(true);
			button[4][24].setSelected(true);
		}
		if (e.getSource() == song2) {
			columns = 33;
			buttonSetup();

			button[5][0].setSelected(true);
			button[6][2].setSelected(true);
			button[7][4].setSelected(true);

			button[5][8].setSelected(true);
			button[6][10].setSelected(true);
			button[7][12].setSelected(true);

			button[7][16].setSelected(true);
			button[7][17].setSelected(true);
			button[7][18].setSelected(true);
			button[7][19].setSelected(true);
			button[6][20].setSelected(true);
			button[6][21].setSelected(true);
			button[6][22].setSelected(true);
			button[6][23].setSelected(true);
			button[5][24].setSelected(true);
			button[6][26].setSelected(true);
			button[7][28].setSelected(true);
		}
		if (e.getSource() == song3) {
			columns = 29;
			buttonSetup();

			button[5][0].setSelected(true);
			button[5][1].setSelected(true);
			button[5][2].setSelected(true);

			button[5][4].setSelected(true);
			button[5][5].setSelected(true);
			button[5][6].setSelected(true);

			button[5][8].setSelected(true);
			button[3][9].setSelected(true);
			button[7][10].setSelected(true);
			button[6][11].setSelected(true);
			button[5][12].setSelected(true);

			button[4][13].setSelected(true);
			button[4][14].setSelected(true);
			button[4][15].setSelected(true);
			button[4][16].setSelected(true);
			button[4][17].setSelected(true);
			button[5][18].setSelected(true);
			button[5][19].setSelected(true);
			button[5][20].setSelected(true);
			button[5][21].setSelected(true);
			button[6][22].setSelected(true);
			button[6][23].setSelected(true);
			button[5][24].setSelected(true);
			button[6][25].setSelected(true);
			button[3][27].setSelected(true);
		}
	}

	public void buttonSetup() {
		panel.removeAll();
		frame.remove(panel);
		panel = new JPanel();
		button = new JToggleButton[8][columns];
		panel.setLayout(new GridLayout(button.length, button[0].length, 2, 2));
		for(int y = 0; y < button.length; y++) {
		    for(int x = 0; x < button[0].length; x++) {
                button[y][x] = new JToggleButton();
				button[y][x].setBackground(Color.GRAY);
				switch (y) {
					case 0: button[y][x].setText("C"); break;
					case 1: button[y][x].setText("B"); break;
					case 2: button[y][x].setText("A"); break;
					case 3: button[y][x].setText("G"); break;
					case 4: button[y][x].setText("F"); break;
					case 5: button[y][x].setText("E"); break;
					case 6: button[y][x].setText("D"); break;
					case 7: button[y][x].setText("C"); break;
				}
                panel.add(button[y][x]);
            }
		}
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.revalidate();
	}

	public static void main(String args[]) {
		Main app = new Main();
	}
}
