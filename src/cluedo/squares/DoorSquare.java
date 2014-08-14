package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;

import cluedo.Position;

/**
 * Square to represent the doorways between the hallway and a room.
 * Properties of a door square:
 * 		-Has a room to connect to and a hall square that is directly next to it.
 * 		-A door square can only be used from the hall square directly ajacent to it.
 * 		-When entering a room, after the door square the player is transported to the middle connected room
 * 		TODO: when exiting a room, is the door square a move? or do you go to outside it?
 * @author myles
 *
 */
public class DoorSquare implements Square {
	private Position position;
	//private HallSquare hallSquare;
	//private Room room;
	private Color color;

	public DoorSquare(Position position) {
		this.position = position;
		//this.hallSquare = hs;
		//this.room = room;
		this.color = Color.RED;

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
		System.out.print('D');
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
