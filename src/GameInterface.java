import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class GameInterface {
	public static JFrame frame;
	private Dimension maxSize;
	private Container container;
	private JLabel title;
	private JPanel board;
	private JPanel scores;
	private JPanel cTurn;
	private static JLabel p1Score;
	private static JLabel p2Score;
	private static int p1;
	private static int p2;
	private static Board gameBoard;
	private JPanel mainArea;
	private static JLabel cturnImg;
	
	private static Image classical;
	private static Image heavyshit;
	private static Image titleImage;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		GameInterface game = new GameInterface();
	}
	
	public GameInterface(){
		p1 = 0;
		p2 = 0;
		maxSize = new Dimension(514, 450);
		
		java.net.URL img1 = getClass().getResource("/CT.png");
		classical = Toolkit.getDefaultToolkit().getImage(img1);
		java.net.URL img2 = getClass().getResource("/ST.png");
		heavyshit = Toolkit.getDefaultToolkit().getImage(img2);
		java.net.URL img3 = getClass().getResource("/title.png");
		titleImage = Toolkit.getDefaultToolkit().getImage(img3);
		makeFrame();
		
	
		
	}

	public void makeFrame() {
		//create frame
		frame = new JFrame();	
		frame.setTitle("ShredWars-Java");
		frame.setResizable(false);
		//our container.
		container = frame.getContentPane();
		//layouts.
		title = new JLabel();
		mainArea = new JPanel();
		mainArea.setLayout(new BorderLayout(50, 50));
		
		gameBoard = new Board();
		
		board = gameBoard.retBoard();
		board.setBorder(new LineBorder(Color.BLACK));
		
		//blank area filled in makes board smaller on screen...
		JPanel N = new JPanel();
		N.setBackground(Color.GRAY);
		
		JPanel E = new JPanel();
		E.setBackground(Color.GRAY);
	
		JPanel S = new JPanel();
		S.setBackground(Color.GRAY);
		
		JPanel W = new JPanel();
		W.setBackground(Color.GRAY);
	
		
		
		mainArea.add(board , BorderLayout.CENTER);
		
		//blanks
		mainArea.add(N , BorderLayout.NORTH);
		mainArea.add(E , BorderLayout.EAST);
		mainArea.add(S , BorderLayout.SOUTH);
		mainArea.add(W , BorderLayout.WEST);
	
		mainArea.setBackground(Color.GRAY);
		
		scores = makeRight();
		//borders.
		//title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		//board.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		//scores.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
		//contents for the areas of GUI.
		
		
		title.setIcon(new ImageIcon(titleImage));
		title.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
		//Jpanel container...
		
		title.setPreferredSize(new Dimension(0, 58));
		//container for all Swing/awt elements.
		container.setLayout(new BorderLayout());
		container.add(title, BorderLayout.NORTH);
		container.add(mainArea, BorderLayout.CENTER);
		container.add(scores, BorderLayout.EAST);
		
		

		frame.pack();       
        frame.setMaximumSize(maxSize);
        frame.setSize(514,449);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
	}
	
	public JPanel makeRight()
	{
		JPanel container = new JPanel();		
        container.setBorder(new EmptyBorder(60,10,60,20));
        container.setBackground(Color.GRAY);
        container.setLayout(new GridLayout(1,0));
        
		JPanel rightHandSide = new JPanel();
		rightHandSide.setLayout(new GridLayout(2,0));
		rightHandSide.setBackground(Color.DARK_GRAY);
		
		//Jpanel teams.
		JPanel teams = new JPanel();
		
		
		teams.setLayout(new GridLayout(0,1));
		teams.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY)," Team Name:"));
		//player1
		JLabel p1 = new JLabel("Classical" , JLabel.CENTER); 
	    p1Score = new JLabel("0", JLabel.CENTER);
        //player2
        JLabel p2 = new JLabel("Heavy Shred" , JLabel.CENTER);
        p2Score = new JLabel("0", JLabel.CENTER);
        teams.add(p1);
        teams.add(p1Score);        
        teams.add(p2);
        teams.add(p2Score);
        
        cTurn = new JPanel();
        cTurn.setLayout(new GridLayout(0,1));
        cTurn.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY)," Current Turn:"));
        cturnImg = new JLabel();        
        cturnImg.setIcon(new ImageIcon(classical));        
        cTurn.add(cturnImg);
        
        
		
		
		rightHandSide.add(teams);
		rightHandSide.add(cTurn);
		rightHandSide.setBorder(new LineBorder(Color.DARK_GRAY));
		//you god! preffered size of each component
		container.setPreferredSize(new Dimension(128, 125));
		container.add(rightHandSide);
		
		return container;
	}
	
	//win game message, team name for C and S and team is just for pop-up text plays no part in game logic
	public static void MessageGameWin(String teamName) {
		String team = "";
		if(teamName.equals("C")) {
			team = "Classical";
		}
		else {
			team = "Heavy";
		}
		if(gameBoard.retWin2Gui() == true) {
		   gameBoard.playWinnerSong(teamName);			  
		   JOptionPane.showMessageDialog(frame,  new JLabel(team + " wins", SwingConstants.CENTER ), "Winner winner!", JOptionPane.PLAIN_MESSAGE);
		   //update sesh score via simple ints and guis box jlabel  
		   UpdateScore(teamName);
		   gameBoard.resetGame();
	    }

    }
	
	public static void MessageNoWinner() {
		JOptionPane.showMessageDialog(frame, "No winner!", "No Winner Play Again!", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void UpdateScore(String teamName) {
		if(teamName.equals("C")) {
			p1++;
			p1Score.setText(p1 + "");			
		}
		else if(teamName.equals("S")) {
			p2++;
			p2Score.setText(p2 + "");			
		}
		
	}
	
	public static void setImageTurn() {
		//classicals turn
		if(gameBoard.CurrentPlayer()) {
			cturnImg.setIcon(new ImageIcon(classical));   
		}
		//shred turn
		else {
			cturnImg.setIcon(new ImageIcon(heavyshit));  
		}
	}
}
