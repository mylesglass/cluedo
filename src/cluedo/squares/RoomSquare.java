package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;

import cluedo.Position;

/**
 * Represents a square that is contained within a room.
 *
 * Properties of a Room Square:
 * 		-Only the center square of a room is stood on by a player.
 * 		-They don't do much.
 * @author myles
 *
 */
public class RoomSquare implements Square {
	private Position position;
	private Color color;

	public RoomSquare(Position position) {
		this.position = position;
		this.color = Color.DARK_GRAY;
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
		System.out.print('R');
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
	}
}