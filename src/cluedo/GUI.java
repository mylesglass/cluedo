package cluedo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import cluedo.squares.Square;

/**
 * Responsible for displaying any information that the user needs to play the game.
 * @author myles
 *
 */
public class GUI {
	private final int SQUARE_SIZE = 20;
	private final int PANEL_SIZE = 300;
	private final int MENU_BAR_SIZE = 50;

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
	private JFrame frame;
	private JComponent drawing;
	private Board board;
	private String STATE = "GAME";

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
	public void initialiseGameInterface(int boardWidth, int boardHeight) {
		// Set dimensions with regards to board size
		this.boardWidth = boardWidth * SQUARE_SIZE;
		this.boardHeight = boardHeight * SQUARE_SIZE; // plus the hieght of any other components

		// Set dimensions for other panels
		this.checkPanelWidth = PANEL_SIZE;
		this.checkPanelHeight = this.boardHeight;

		this.playerPanelWidth = this.boardWidth + checkPanelWidth;
		this.playerPanelHeight = PANEL_SIZE;

		this.gameWidth = playerPanelWidth;
		this.gameHeight = playerPanelHeight + this.boardHeight + MENU_BAR_SIZE;

		// Set Frame
		frame = new JFrame("Guess Who");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(this.gameWidth, this.gameHeight);

		// Drawing Component
		drawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				redraw(g);
			}
		};
		frame.add(drawing, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	/**
	 * Draws
	 */
	public void draw() {
		drawing.repaint();
	}

	/**
	 * Update the reference to the games board.
	 * @param board
	 */
	public void updateBoard(Board board) {
		// I couldn't really think of another simple solution to get a reference ot the board
		// in the gui class, and I needed it as I want to draw everything in here.
		this.board = board;
	}

	/**
	 * Executes the correct method for drawing depending on the state of the screen.
	 */
	private void redraw(Graphics g) {
		drawBackgrounds(g);
		if(STATE.equals("GAME")) drawGame(g);
	}

	/**
	 * Draws the background panels for each of the panels
	 */
	private void drawBackgrounds(Graphics g) {
		// Board
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, MENU_BAR_SIZE, boardWidth, boardHeight);

		// CheckList
		g.setColor(Color.WHITE);
		g.fillRect(boardWidth, MENU_BAR_SIZE, checkPanelWidth, checkPanelHeight);

		// Player Panel
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, MENU_BAR_SIZE + boardHeight, playerPanelWidth, playerPanelHeight);
	}


	/**
	 * Draws the main game components onto the screen.
	 * This includes the board and the control panel for the game.
	 * @param graphics to draw to.
	 */
	private void drawGame(Graphics g) {
		board.draw(g, SQUARE_SIZE, MENU_BAR_SIZE);
		// Draws Characters on board, and anything else that overlays board

		// Draws Lower Control Panel

	}
}
