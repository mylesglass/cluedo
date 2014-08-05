package cluedo.squares;

import java.awt.Color;

import cluedo.Position;

/**
 * Tunnel Squares connect the corner rooms together. 
 * @author myles
 *
 */
public class TunnelSquare implements Square {
	private Position position;
	private Color color;
	private TunnelSquare pairSquare;
	
	public TunnelSquare(Position position) {
		this.position = position;
		this.color = Color.MAGENTA;
	}
	
	/**
	 * Add the Tunnel Square that this square links to
	 * @param TunnelSquare
	 * 
	 * TODO: how to do errors? need to add exceptions
	 */
	public void addPair(TunnelSquare pair) {
		if(this.pairSquare != null) {
			System.out.printf("Attempting to give tunnel at (%d,%d) a pair, but it already has one!", position.getX(), position.getY());
		}
		else {
			this.pairSquare = pair;
		}
	}
	
	/**
	 * Get the position of this square
	 * @return position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Get the color of this square
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Print out char representing type of square. For testing purposes.
	 */
	public void print() {
		System.out.print('T');
	}
}
