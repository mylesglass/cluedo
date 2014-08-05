package cluedo;

/**
 * Represents an (x,y) coordinate position on the board
 * @author myles
 */

public class Position {
	private int x;
	private int y;
	
	/**
	 * Construct position
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get x coordinate of position
	 * @return x
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Get y coordinate of position
	 * @return y
	 */
	public int getY() {
		return this.y;
	}
}
