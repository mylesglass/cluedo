package cluedo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cluedo.squares.Square;

/**
 * Responsible for displaying any information that the user needs to play the game.
 * @author myles
 *
 */
public class GUI {
	private final int SQUARE_SIZE = 20;
	private final int PANEL_SIZE = 300;
	private final int MENU_BAR_SIZE = 60;

	private int gameWidth;
	private int gameHeight;

	private int boardWidth;
	private int boardHeight;

	private int checkPanelWidth;
	private int checkPanelHeight;

	private int playerPanelWidth;
	private int playerPanelHeight;

	// Checklist information
	public ArrayList<String> roomNames;
	public ArrayList<String> characterNames;
	public ArrayList<String> weaponNames;

	//number of players, storing here temporarily.
	private int numPlayers;

	// GUI Components
	private BoardPanel boardPanel;
	private CheckListPanel checkListPanel;
	private PlayerPanel playerPanel;
	private JFrame container;

	private Board board;
	private String state = "GAME";

	private Font diceFont = new Font("Dialog", Font.PLAIN, 36);

	/**
	 * Construct GUI Component for displaying any information needed for the user to play game
	 */
	public GUI() {

	}

	/**
	 * Sets up various interface components needed to display GUI
	 * @param boardWidth
	 * @param boardHeight
	 */
	@SuppressWarnings("serial")
	public void initialiseGameInterface(Board board) {
		this.board = board;

		// Set dimensions with regards to board size
		this.boardWidth = board.getWidth() * SQUARE_SIZE;
		this.boardHeight = board.getHeight() * SQUARE_SIZE; // plus the hieght of any other components

		// Set dimensions for other panels
		this.checkPanelWidth = PANEL_SIZE;
		this.checkPanelHeight = this.boardHeight;

		this.playerPanelWidth = this.boardWidth + checkPanelWidth;
		this.playerPanelHeight = PANEL_SIZE;

		this.gameWidth = playerPanelWidth;
		this.gameHeight = playerPanelHeight + this.boardHeight + MENU_BAR_SIZE;

		// Set Frame
		container = new JFrame("Guess Who");
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setSize(this.gameWidth, this.gameHeight + 25);
		container.setLayout(null);

		// Menu Bar
		JPanel menuPanel = new JPanel();
		JButton newGameButton = new JButton("New Game");
        menuPanel.add(newGameButton);
		container.add(menuPanel);
		menuPanel.setBounds(0, 0, gameWidth, MENU_BAR_SIZE);

		// Board Drawing Component
		boardPanel = new BoardPanel(board);
		boardPanel.setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));

		boardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyUtils.Log("[GUI][BOARD] Mouse clicked on a "+getSquareAt(e.getX() / SQUARE_SIZE, e.getY() / SQUARE_SIZE).toString());
			}
		});

		//CheckList Drawing Component
		checkListPanel = new CheckListPanel(checkPanelWidth, checkPanelHeight);
		checkListPanel.setPreferredSize(new Dimension(this.checkPanelWidth, this.checkPanelHeight));
		checkListPanel.setBackground(Color.GRAY);

		// Player Panel Component
		playerPanel = new PlayerPanel(playerPanelWidth, playerPanelHeight);
		checkListPanel.setPreferredSize(new Dimension(this.playerPanelWidth, this.playerPanelHeight));

		// Add Panels to Frame
		container.add(boardPanel);
		boardPanel.setBounds(0, MENU_BAR_SIZE + 0, boardWidth, boardHeight);
		container.add(checkListPanel);
		checkListPanel.setBounds(boardWidth, MENU_BAR_SIZE + 0, PANEL_SIZE, boardHeight);
		container.add(playerPanel);
		playerPanel.setBounds(0, MENU_BAR_SIZE + boardHeight, gameWidth, PANEL_SIZE);

		// Once all set up, make it visible
		container.setVisible(true);


		//NewGame button press events
		newGameButton.addActionListener(new ActionListener(){

        	public void actionPerformed(ActionEvent e) {

                String[] options = new String[] {"1", "2","3","4","5","6"};
                int name = JOptionPane.showOptionDialog(container,"How many players?" ,"number of players?",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null ,options,options[0]);
        		 numPlayers = name+1;

                MyUtils.Log("[GUI] New Game button pushed: " + numPlayers + " player selected");
        	}});
	}

	public void updateCardNames(ArrayList<String> rooms, ArrayList<String> chars, ArrayList<String> weapons) {
		this.characterNames = chars;
		this.roomNames = rooms;
		this.weaponNames = weapons;
	}

	private Square getSquareAt(int x, int y) {
		return this.board.getSquareAt(x,y);
	}

	public void setState(String state) {
		this.state = state;
	}

	public void drawGame() {
		drawBoard();
		drawCheckList();
		drawPlayerPanel();
	}

	private void drawBoard() {

		boardPanel.repaint();
	}

	private void drawCheckList() {
		Graphics clg = checkListPanel.getGraphics();

		clg.setColor(Color.GRAY);
		clg.fillRect(boardWidth, 0, PANEL_SIZE, boardHeight);

		clg.setColor(Color.WHITE);

		int y = PANEL_SIZE/8;
		for(String room : roomNames) {
			clg.drawString(room, PANEL_SIZE/8, y);
			y += PANEL_SIZE / 8;
			//MyUtils.Log("[GUI] Drawing "+room+" to Checklist ("+PANEL_SIZE/8+","+y+")");
		}
		checkListPanel.repaint();
	}

	private void drawPlayerPanel() {
		playerPanel.repaint();
	}


}

class BoardPanel extends JPanel {
	private final int SQUARE_SIZE = 20;
	private int width;
	private int height;
	private Board board;

	public BoardPanel(Board board) {
		this.board = board;
		this.width = board.getWidth() * SQUARE_SIZE;
		this.height = board.getHeight() * SQUARE_SIZE;
		MyUtils.Log("[BoardPanel] Board Panel Created. Size: "+this.width+", "+this.height);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		board.draw(g, SQUARE_SIZE, 0);
	}
}

class CheckListPanel extends JPanel {
	private int width;
	private int height;

	public CheckListPanel(int width, int height) {
		this.width = width;
		this.height = height;
		MyUtils.Log("[CheckListPanel] Check List Panel Created. Size: "+width+", "+height);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawString, x, y);
	}
}

class PlayerPanel extends JPanel {
	private int width;
	private int height;

	public PlayerPanel(int width, int height) {
		this.width = width;
		this.height = height;
		MyUtils.Log("[PlayerPanel] Player Panel Created. Size: "+width+", "+height);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Player Panel", 100, 100);
	}

}


