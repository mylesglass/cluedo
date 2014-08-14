package cluedo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
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

	private int width;
	private int height;

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

		// Drawing Component

		boardPanel = new BoardPanel(board);
		boardPanel.setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));

		checkListPanel = new CheckListPanel(checkPanelWidth, checkPanelHeight);
		checkListPanel.setPreferredSize(new Dimension(this.checkPanelWidth, this.checkPanelHeight));

		playerPanel = new PlayerPanel(playerPanelWidth, playerPanelHeight);
		checkListPanel.setPreferredSize(new Dimension(this.playerPanelWidth, this.playerPanelHeight));


		container.add(boardPanel);
		boardPanel.setBounds(0, MENU_BAR_SIZE + 0, boardWidth, boardHeight);

		container.add(checkListPanel);
		checkListPanel.setBounds(boardWidth, MENU_BAR_SIZE + 0, PANEL_SIZE, boardHeight);

		container.add(playerPanel);
		playerPanel.setBounds(0, MENU_BAR_SIZE + boardHeight, gameWidth, PANEL_SIZE);

		// Once all set up, make it visible
		container.setVisible(true);
	}

	public void drawBoard() {
		boardPanel.repaint();
	}

	public void drawCheckList() {
		checkListPanel.repaint();
	}

	public void drawPlayerPanel() {
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
		g.setColor(Color.RED);
		g.fillRect(0, 0, width, height);
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
	}
}


