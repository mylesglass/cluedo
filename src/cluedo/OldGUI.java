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
public class OldGUI {
	private int width;
	private int height;
	private JFrame frame;
	private JComponent drawing;
	private Board board;
	private String STATE = "GAME";

	/**
	 * Construct GUI Component for displaying any information needed for the user to play game
	 */
	public OldGUI() {

	}

	private final int SQUARESIZE = 15;

	/**
	 * Sets up various interface components needed to display GUI
	 * @param boardWidth
	 * @param boardHeight
	 */
	@SuppressWarnings("serial")
	public void setupInterface(int boardWidth, int boardHeight) {
		// Set dimensions with regards to board size
		this.width = boardWidth * SQUARESIZE;
		this.height = boardHeight * SQUARESIZE; // plus the hieght of any other components

		// Set Frame
		frame = new JFrame("Cluedo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(this.width, this.height);

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
		if(STATE.equals("GAME")) drawGame(g);
	}

	/**
	 * Draws the main game components onto the screen.
	 * This includes the board and the control panel for the game.
	 * @param graphics to draw to.
	 */
	private void drawGame(Graphics g) {
		drawBoard(g);
		// Draws Characters on board, and anything else that overlays board

		// Draws Lower Control Panel

	}

	/**
	 * Draws all board squares onto supplied graphics element
	 * @param g
	 */
	private void drawBoard(Graphics g) {
		// Draws board squares,
		for(int i = 0; i < board.getHeight(); i++) {
			for(int j = 0; j < board.getWidth(); j++) {
				// For each square within board, draw a 15 pixel coloured square to represent it, with a black 1px border.
				Square current = board.getSquareAt(j, i);
				g.setColor(current.getColor());
				g.fillRect(j * 15, i * 15, 15, 15);
				g.setColor(Color.BLACK);
				g.drawRect(j*15, i*15, 15, 15);
			}
		}

	}
}
