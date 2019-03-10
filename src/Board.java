import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import javafx.scene.control.ToolBar;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Board {
	private JPanel board;
	private boolean winner;
	private JButton [][] tiles;
	private static final int SIZE = 3;
	private boolean classic;
	private ArrayList<String> colors;

	private static Image classical;
	private static Image heavyshit;
	private static Image Img;
	
	private java.net.URL soundC;
	private java.net.URL soundS;
	private int resetCounter;
	// music

	
	
	public Board() {
         soundC = getClass().getResource("/classic.wav");
		
		soundS = getClass().getResource("/shred.wav");
		
		
		java.net.URL img1 = getClass().getResource("/C.PNG");
		classical = Toolkit.getDefaultToolkit().getImage(img1);
		
		java.net.URL img2 = getClass().getResource("/S.PNG");
		heavyshit = Toolkit.getDefaultToolkit().getImage(img2);
		
		java.net.URL img3 = getClass().getResource("/D.png");
		Img = Toolkit.getDefaultToolkit().getImage(img3);
	
		
		
		resetCounter = 0;
		classic = true;
		tiles = new JButton[SIZE][SIZE];
		board = new JPanel();
		winner = false;
		board.setLayout(new GridLayout(SIZE, SIZE));
		board.setBorder(new LineBorder(Color.BLACK));		
		createBoard();
		retBoard();
		
		
	}
	

	public  void sound(URL u){
		InputStream m;
		
		try {		
			String url = u.toString();
			AudioClip a = Applet.newAudioClip(new URL(url));
			a.play();
			
		}
		catch(Exception e){
			
		}
		
	}
	
	
	public void playWinnerSong(String teamName) {
		
		try{
		if(teamName.equals("C")) {
			sound (soundC);
		}
		else {
			
			sound(soundS);
			Thread.sleep(10);
		}
		}
		catch(Exception e) {
			
		}
	}
	
	public void createBoard() {
		
		for(int x = 0; x < SIZE; x++) {
			for(int y = 0; y < SIZE; y++) {
				final int finX = x;
				final int finY = y;
				tiles[finX][finY] = new JButton();
				tiles[finX][finY].addActionListener(e -> click(finX, finY));
				//tiles[finX][finY].setBackground(Color.GRAY);
				tiles[x][y].setIcon(new ImageIcon(Img));
				tiles[finX][finY].setBorder(new LineBorder(Color.BLACK));
				tiles[finX][finY].setText("E");
				tiles[finX][finY].setForeground(Color.GRAY);
				//remove selected jbutton weird blue outline
				tiles[finX][finY].setFocusPainted(false);
				board.add(tiles[x][y]);
			}
			
		}
	}
	
	public void resetGame() {
		GameInterface.setImageTurn();
		for(int x = 0 ; x < SIZE ; x++) {
			for(int y = 0; y < SIZE; y++) {
				tiles[x][y].setIcon(new ImageIcon(Img));
				tiles[x][y].setBorder(new LineBorder(Color.BLACK));
				tiles[x][y].setText("E");
				tiles[x][y].setEnabled(true);
			}
			resetCounter = 0;
			GameInterface.setImageTurn();
		}
	}
	
	public JPanel retBoard()
	
	{
		
		return board;
	}
	
	public void click(int x , int y) {	
		GameInterface.setImageTurn();
		
		if(classic) {				
			tiles[x][y].setText("C");			
			tiles[x][y].setEnabled(false);
			//disabled icon set
			tiles[x][y].setDisabledIcon(new ImageIcon(classical));
			checkWin("C");
			classic = false;
			GameInterface.setImageTurn();
		}
		else {	
			tiles[x][y].setText("S");
			tiles[x][y].setEnabled(false);
			//disabled icon set...
			tiles[x][y].setDisabledIcon(new ImageIcon(heavyshit));
			checkWin("S");
			classic = true;	
			GameInterface.setImageTurn();
		}
	}
	
	public void checkWin(String teamName) {
		boolean win = false;
		colors = new ArrayList<String>();
		for(int x = 0 ; x < 3; x++) {
			for(int y= 0; y < 3; y++) {				
				String team = tiles[x][y].getText();
				colors.add(team);				
				}						
			}
				//colors position					
				String zero = colors.get(0);					
				String one = colors.get(1);
				String two = colors.get(2);
				String three = colors.get(3);
				String four = colors.get(4);
				String five = colors.get(5);
				String six = colors.get(6);
				String seven = colors.get(7);
				String eight = colors.get(8);		
			
				//if position 0,1,2 top line match same team.../
				if(zero.equals(teamName) && one.equals(teamName) && two.equals(teamName)) {
					win = true;
					System.out.println("win!" + teamName);
				}
				//if vertical 0,3,6 top left down same team.../
				else if (zero.equals(teamName) && three.equals(teamName) && six.equals(teamName)) {
					win = true;
					System.out.println("win!" + teamName);
				}
				//if top 0,4,8 diagonal win.../
				else if(zero.equals(teamName) && four.equals(teamName) && eight.equals(teamName)) {
					win = true;
					System.out.println("win!" + teamName);
				}
				//if bottom left diagonal win 6,4,2.../
				else if(six.equals(teamName) && four.equals(teamName) && two.equals(teamName)) {
					win = true;
					System.out.println("win!" + teamName);
				}
				//from bottom right 8,5,2.../
				else if(eight.equals(teamName) && five.equals(teamName) && two.equals(teamName)) {
					win = true;
					System.out.println("win!" + teamName);
				}
				//from bottom right 8,7,6.../
				else if(eight.equals(teamName) && seven.equals(teamName) && six.equals(teamName)) {
					win = true;
					System.out.println("win!" + teamName);
				}
				// 3,4,5 across in middle.../
				else if(three.equals(teamName) && four.equals(teamName) && five.equals(teamName)) {
					win = true;
					System.out.println("win!" + teamName);
				}
				// down 1,4,7 across middle .../
				else if (one.equals(teamName) && four.equals(teamName) && seven.equals(teamName)) {
					win = true;										
				}
				else {
					System.out.println("no win yet!");
					winner = false;
				}
				
				if(win == true) {
					winner = true;
					GameInterface.setImageTurn();
					resetCounter = 0;
					}
				//run check winner...
				resetCounter++;
				if(resetCounter == 9) {
					GameInterface.MessageNoWinner();
					resetGame();
					resetCounter = 0;
					GameInterface.setImageTurn();
				}
				GameInterface.MessageGameWin(teamName);
				
			
	}
	
	public boolean retWin2Gui() {
		//ret gui the win conditon...
		return winner;
		
	}
	
	public boolean CurrentPlayer() {
		return classic;
	}
	
	
	
}
	
	
	

	
	

