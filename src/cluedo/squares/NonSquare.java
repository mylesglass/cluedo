package cluedo.squares;

import java.awt.Color;

import cluedo.Position;


/**
 * Represents a square that cannot be used.
 * TODO: is this needed? because there are oter squares 
 * 			like roomsquare that are never actually stood on?
 * 			actually looking at a board it might be.
 * 
 * @author myles
 *
 */
public class NonSquare implements Square {
	private Position position;
	private Color color;
	
	public NonSquare(Position position) {
		this.position = position;
		this.color = Color.BLACK;
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
		System.out.print('N');
	}
}
