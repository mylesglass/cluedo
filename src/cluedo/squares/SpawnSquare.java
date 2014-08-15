package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;

import cluedo.Position;

public class SpawnSquare implements Square{
	private Color color;
	private Position position;

	public SpawnSquare(Position position) {
		this.position = position;
		color = Color.DARK_GRAY;
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
		System.out.print('S');
	}

	/**
	 * Return string reference to this
	 * @return string
	 */
	public String toString() {
		return "SpawnSquare("+position.getX()+","+position.getY()+")";
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
