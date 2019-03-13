import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class gui extends JPanel implements ActionListener {
	JFrame 		frame;
	JMenu 		font, fontSize, textColor, background, outline;
    JMenuBar 	menuBar;
	JButton 	north, south, east, west, reset;
	JPanel 		buttonPanel;
	JTextArea 	area;
    JMenuItem 	font1, font2, font3;
    JMenuItem 	size1, size2, size3;
    JMenuItem 	color1, color2, color3;
    JMenuItem 	background1, background2, background3;
    JMenuItem 	outline1, outline2, outline3, outline4;
    int 		size;
    JMenu[] 	menuArr;
    JButton[] 	buttonArr;
    JMenuItem[] itemArr;
	String		fontName;


	public gui() {
		frame = new JFrame();

		buttonPanel	= new JPanel();

        north   =	new JButton( "NORTH" );
        south   =	new JButton( "SOUTH" );
        east    =	new JButton(  "EAST" );
        west    =	new JButton(  "WEST" );
		reset	=	new JButton( "RESET" );

        menuBar	=	new JMenuBar();

        font        =   new JMenu(      "Fonts" );
        fontSize    =   new JMenu(      "Sizes" );
        textColor   =   new JMenu(      "Color" );
        background  =   new JMenu( "Background" );
        outline     =   new JMenu(    "Outline" );

        font1   =   new JMenuItem(           "Arial" );
        font2   =   new JMenuItem( "Times New Roman" );
        font3   =   new JMenuItem(         "Cambria" );

        size1   =   new JMenuItem( "10" );
        size2   =   new JMenuItem( " 5" );
        size3   =   new JMenuItem( "12" );

        color1  =   new JMenuItem(    "Red" );
        color2  =   new JMenuItem(   "Blue" );
        color3  =   new JMenuItem( "Random" );

        background1 =   new JMenuItem(    "Red" );
        background2 =   new JMenuItem(   "Blue" );
        background3 =   new JMenuItem( "Random" );

        outline1    =   new JMenuItem( "No Color" );
        outline2    =   new JMenuItem(      "Red" );
        outline3    =   new JMenuItem(     "Blue" );
        outline4    =   new JMenuItem(   "Random" );

        menuArr     =   new JMenu[] 	{font, fontSize, textColor, background, outline};
        buttonArr   =   new JButton[]   {north, south, east, west, reset};
        itemArr     =   new JMenuItem[] {font1, font2, font3, size1, size2, size3, color1, color2, color3, background1, background2, background3, outline1, outline2, outline3, outline4};

		frame.add(this);

        font.add(font1);
        font.add(font2);
        font.add(font3);

        font1.addActionListener(this);
        font2.addActionListener(this);
        font3.addActionListener(this);

        fontSize.add(size1);
        fontSize.add(size2);
        fontSize.add(size3);

        size1.addActionListener(this);
        size2.addActionListener(this);
        size3.addActionListener(this);

        textColor.add(color1);
        textColor.add(color2);
        textColor.add(color3);

        color1.addActionListener(this);
        color2.addActionListener(this);
        color3.addActionListener(this);

        background.add(background1);
        background.add(background2);
        background.add(background3);

        background1.addActionListener(this);
        background2.addActionListener(this);
        background3.addActionListener(this);

        outline.add(outline1);
        outline.add(outline2);
        outline.add(outline3);
        outline.add(outline4);

        outline1.addActionListener(this);
        outline2.addActionListener(this);
        outline3.addActionListener(this);
        outline4.addActionListener(this);

        north.addActionListener(this);
        south.addActionListener(this);
        east .addActionListener(this);
        west .addActionListener(this);
		reset.addActionListener(this);

        menuBar.add(       font );
        menuBar.add(   fontSize );
        menuBar.add(  textColor );
        menuBar.add( background );
        menuBar.add(    outline );


        north.setPreferredSize( new Dimension(10, 10) );
        south.setPreferredSize( new Dimension(10, 10) );
        east.setPreferredSize(  new Dimension(10, 10) );
        west.setPreferredSize(  new Dimension(10, 10) );


        menuBar.setLayout(new GridLayout(1, 5));

        buttonPanel.setLayout(new GridLayout(1, 6));
        buttonPanel.add(   north );
        buttonPanel.add(   south );
        buttonPanel.add(    east );
        buttonPanel.add(    west );
		buttonPanel.add(   reset );
        buttonPanel.add( menuBar );

		restart();

        frame.add(buttonPanel, BorderLayout.NORTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500,600);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

        if (Arrays.asList(buttonArr).indexOf(e.getSource()) != -1 && e.getSource() != reset) {

            double x = 0.0;
            String text = ((JButton)(e.getSource())).getText();
            switch (text) {
                case "NORTH":   x =  2.5; break;
                case "SOUTH":   x =  2.5; break;
                case  "EAST":   x = -2.5; break;
                case  "WEST":   x = -2.5; break;
            }

            frame.remove(buttonPanel);
            buttonPanel.setLayout( new GridLayout((int)(3.5 - x), (int)(3.5 + x)) );
			menuBar.    setLayout( new GridLayout((int)(3.5 - x), (int)(3.5 + x)) );
            buttonPanel.add(menuBar);

            switch (text) {
                case "NORTH":   frame.add( buttonPanel, BorderLayout.NORTH ); break;
                case "SOUTH":   frame.add( buttonPanel, BorderLayout.SOUTH ); break;
                case  "EAST":   frame.add( buttonPanel, BorderLayout. EAST ); break;
                case  "WEST":   frame.add( buttonPanel, BorderLayout. WEST ); break;
            }
        }


		if (e.getSource() == font1) fontName =           "Arial";
		if (e.getSource() == font2) fontName = "Times New Roman";
		if (e.getSource() == font3) fontName =         "Cambria";

		if (e.getSource() == size1) size = 10;
		if (e.getSource() == size2) size =  5;
		if (e.getSource() == size3) size = 12;

		for (JMenu     menu :   menuArr) { menu.  setFont(new Font(fontName, Font.PLAIN, size)); }
        for (JButton button : buttonArr) { button.setFont(new Font(fontName, Font.PLAIN, size)); }
        for (JMenuItem item :   itemArr) { item.  setFont(new Font(fontName, Font.PLAIN, size)); }

		font1.setFont(new Font(			 "Arial", Font.PLAIN, size));
		font2.setFont(new Font("Times New Roman", Font.PLAIN, size));
		font3.setFont(new Font(		   "Cambria", Font.PLAIN, size));


		if (e.getSource() == reset) restart();

        frame.revalidate();
	}

	public void restart() {
		fontName = "Arial";
		size 	 = 		12;

		for (JMenu     menu :   menuArr) { menu.  setFont(new Font(fontName, Font.PLAIN, size)); }
        for (JButton button : buttonArr) { button.setFont(new Font(fontName, Font.PLAIN, size)); }
        for (JMenuItem item :   itemArr) { item.  setFont(new Font(fontName, Font.PLAIN, size)); }

		font1.setFont(new Font(			 "Arial", Font.PLAIN, size));
		font2.setFont(new Font("Times New Roman", Font.PLAIN, size));
		font3.setFont(new Font(		   "Cambria", Font.PLAIN, size));

		frame.remove(buttonPanel);
		buttonPanel.setLayout(new GridLayout(1, 6));
		menuBar    .setLayout(new GridLayout(1, 5));
		frame.add(buttonPanel, BorderLayout.NORTH);
	}


	public static void main(String[] args) {
		gui app = new gui();
	}

}
