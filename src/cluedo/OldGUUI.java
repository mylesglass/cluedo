package cluedo;


import java.awt.BorderLayout;
import java.awt.Color;
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
public class OldGUUI {
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
	private JFrame frame;
	private JComponent drawing;
	private Board board;
	private String state = "GAME";

	private Font diceFont = new Font("Dialog", Font.PLAIN, 36);

	/**
	 * Construct GUI Component for displaying any information needed for the user to play game
	 */
	public OldGUUI() {

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

		// Once all set up, make it visible
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

	public void setState(String state) {
		this.state = state;
		draw();
	}

	/**
	 * Visually communicated to user that the mouse has been rolled.
	 * Draws rolling graphic, and displays result.
	 * @param result
	 */
	private int result;
	private boolean rolling;

	public void roll(int result) {
		int interval = 2; // full time of roll in milliseconds
		rolling = true;
		while(interval < 800) {
			this.result = (int) (1 + Math.random() * 6);
			draw();
			MyUtils.Pause(interval);
			interval = (int) (interval * 1.5);

		}
		this.result = result;
		draw();
	}

	/**
	 * Draws Graphical repersentation of dice result.
	 * @param g
	 */
	private final int DICE_SIZE = 150;

	private void drawDice(Graphics g) {
		g.setFont(diceFont);
		g.setColor(Color.WHITE);
		g.fillOval((boardWidth / 2) - (DICE_SIZE / 2), (boardHeight / 2) - (DICE_SIZE / 2), DICE_SIZE, DICE_SIZE);
		g.setColor(Color.DARK_GRAY);
		g.drawString(""+this.result, (boardWidth / 2) - 10, (boardHeight / 2) + 10);
	}

	/**
	 * Executes the correct method for drawing depending on the state of the screen.
	 */
	private void redraw(Graphics g) {
		if(state.equals("GAME")) drawGame(g);
		else if(state.equals("MENU")) drawMainMenu(g);
	}

	/**
	 * Draws the main game components onto the screen.
	 * This includes the board and the control panel for the game.
	 * @param graphics to draw to.
	 */
	private void drawGame(Graphics g) {
		// Panels
		drawCheckPanel(g);
		drawPlayerPanel(g);

		// draw board
		board.draw(g, SQUARE_SIZE, MENU_BAR_SIZE);

		// Draws Characters on board, and anything else that overlays board

		if(rolling) drawDice(g);
	}

	/**
	 * Draw the Player Panel
	 * @param g
	 */
	private void drawPlayerPanel(Graphics g) {
		// background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, MENU_BAR_SIZE + boardHeight, playerPanelWidth, playerPanelHeight);

		g.setColor(Color.WHITE);
		g.drawString("PlayerPanel", 50, MENU_BAR_SIZE + boardHeight + 50);
	}

	/**
	 * Draw the Check List Panel
	 * @param g
	 */
	private void drawCheckPanel(Graphics g) {
		// background
		g.setColor(Color.WHITE);
		g.fillRect(boardWidth, MENU_BAR_SIZE, checkPanelWidth, checkPanelHeight);

		g.setColor(Color.BLACK);
		g.drawString("Checklist", boardWidth + 50, MENU_BAR_SIZE + 50);
	}

	/**
	 * Draw main menu for game
	 * @param g
	 */
	private void drawMainMenu(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("Welcome to Cluedo", 100, 100);
		g.drawString("Press ENTER to begin", 100, 130);
	}
}


