package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cluedo.Position;


/**
 * Square to represent any walkable area in the hallways of game board.
 *
 * Properties of hallway squares are:
 * 		-Can have players on them.
 * 		-Each move of a player on a hall square uses one of their roll count (?)
 * @author myles
 *
 */
public class HallSquare implements Square {
	private Position position;
	private Color color;
	private Boolean doorNear = false;
	private Image image;

	public HallSquare(Position position) {
		this.position = position;
		this.color = Color.LIGHT_GRAY;

		try {
			this.image = ImageIO.read(new File("src/images/squares/square-hall.png"));
		} catch (IOException e) {
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
	 * Set Boolean value representing if there is a door next to this square
	 * @param boolean
	 */
	public void setDoorNear(Boolean b) {
		this.doorNear = b;
	}

	/**
	 * Check to see if there is a door near to this hall square
	 * @return true if directly outside door
	 */
	public boolean doorNear() {
		return this.doorNear;
	}

	/**
	 * Print out char representing type of square. For testing purposes.
	 */
	public void print() {
		System.out.print('H');
	}

	/**
	 * Return string reference to this
	 * @return string
	 */
	public String toString() {
		return "HallSquare("+position.getX()+","+position.getY()+")";
	}

	/**
	 * Draws square on supplied graphics object, at defined square size.
	 * @param graphics object to draw on
	 * @param int size of square
	 * @param y Offset cause by menu bar above
	 */
	public void drawSSSS(Graphics g, int squareSize, int yOffset) {
		if(this.doorNear()) this.color = Color.GRAY;
		g.setColor(this.color);
		g.fillRect(position.getX() * squareSize, (position.getY() * squareSize) + yOffset, squareSize, squareSize);
		g.setColor(Color.GRAY);
		g.drawRect(position.getX() * squareSize, (position.getY() * squareSize) + yOffset, squareSize, squareSize);
	}

	public void draw(Graphics g, int squareSize, int menuSize) {
		g.drawImage(this.image,  position.getX() * squareSize,  (position.getY() * squareSize),  squareSize, squareSize, null);
	}
}
