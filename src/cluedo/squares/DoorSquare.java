package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cluedo.Position;
import cluedo.Room;

/**
 * Square to represent the doorways between the hallway and a room.
 * Properties of a door square:
 * 		-Has a room to connect to and a hall square that is directly next to it.
 * 		-A door square can only be used from the hall square directly ajacent to it.
 * 		-When entering a room, after the door square the player is transported to the middle connected room
 * @author myles
 *
 */
public class DoorSquare implements Square {
	private Position position;
	@SuppressWarnings("unused")
	private HallSquare hallSquare;
	private Room room;
	private Color color;
	private Image image;

	public DoorSquare(Position position) {
		this.position = position;
		//this.room = room;
		this.color = Color.RED;
		try {
			this.image = ImageIO.read(new File("src/images/squares/square-door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the attached hall square of this doorsquare,
	 * @param doorSquare to attach
	 */
	public void setHallSquare(HallSquare hs) {
		this.hallSquare = hs;
	}

	/**
	 * Set the room that this door leads to.
	 * @param Room
	 */
	public void setRoom(Room room) {
		this.room = room;
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
	 * returns this doors room.
	 * @return
	 */
	public Room getRoom(){

	return this.room;
	}
	/**
	 * Print out char representing type of square. For testing purposes.
	 */
	public void print() {
		System.out.print('D');
	}

	/**
	 * Return string reference to this
	 * @return string
	 */
	public String toString() {
		return "DoorSquare("+position.getX()+","+position.getY()+")";
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
