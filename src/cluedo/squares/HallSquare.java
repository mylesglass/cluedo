package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;

import cluedo.Position;


/**
 * Square to represent any walkable area in the hallways of game board.
 *
 * Properties of hallway squares are:
 * 		-Can have players on them.
 * 		-Each move of a player on a hall square uses one of their roll count (?)
 * 		- TODO: can a player share a hall square????
 * @author myles
 *
 */
public class HallSquare implements Square {
	private Position position;
	private Color color;

	public HallSquare(Position position) {
		this.position = position;
		this.color = Color.LIGHT_GRAY;
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
		System.out.print('H');
	}

	/**
	 * Draws square on supplied graphics object, at defined square size.
	 * @param graphics object to draw on
	 * @param int size of square
	 * @param y Offset cause by menu bar above
	 */
	public void draw(Graphics g, int squareSize, int yOffset) {
		g.setColor(this.color);
		g.fillRect(position.getX() * squareSize, (position.getY() * squareSize) + yOffset, squareSize, squareSize);
		g.setColor(Color.GRAY);
		g.drawRect(position.getX() * squareSize, (position.getY() * squareSize) + yOffset, squareSize, squareSize);
	}
}