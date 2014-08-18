package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cluedo.Position;

public class SpawnSquare implements Square{
	private Color color;
	private Position position;
	private Image image;

	public SpawnSquare(Position position) {
		this.position = position;
		color = Color.DARK_GRAY;
		try {
			this.image = ImageIO.read(new File("src/images/squares/square-spawn-alt.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void draw(Graphics g, int squareSize, int menuSize) {
		g.drawImage(this.image,  position.getX() * squareSize,  (position.getY() * squareSize),  squareSize, squareSize, null);
	}
}
