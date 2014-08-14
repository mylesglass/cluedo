package cluedo;

import java.awt.Graphics;

import cluedo.squares.*;

/**
 * Responsible for holding all information regarding the current state of the board.
 * @author myles
 */
public class Board {
	private int width;
	private int height;
	private Square[][] board;

	/**
	 * Construct board object, defined by width and height in squares
	 * @param width in amount of squares
	 * @param height in amount of squares
	 */
	public Board(int width, int height) {
		MyUtils.Log("[Board] Creating board with width: "+width+" height: "+height);
		this.width = width;
		this.height = height;
		this.board = new Square[width][height];
	}

	/**
	 * Add Square to Board at the squares contained position
	 * @param square to add
	 */
	public void addSquare(Square square){
		Position sqpos = square.getPosition();
		board[sqpos.getX()][sqpos.getY()] = square;
	}

	/**
	 * Get Square at supplied x and y coordinates
	 * @param x position
	 * @param y position
	 * @return Square at (x,y)
	 */
	public Square getSquareAt(int x, int y) {
		return board[x][y];
	}

	/**
	 * Get width of board
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Get height of board
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Draws the board on supplied graphics objects.
	 * @param graphics object to draw on
	 * @param square size
	 * @param menu bar size
	 */
	public void draw(Graphics g, int squareSize, int menuSize) {
		for(int i = 0; i < this.getHeight(); i++) {
			for(int j = 0; j < this.getWidth(); j++) {
				this.getSquareAt(j, i).draw(g, squareSize, menuSize);
			}
		}
	}
}
