package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;
import cluedo.Position;
import cluedo.Room;


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
	private int roomnum;
	private Color color;
	private Room room;

	public RoomSquare(Position position, int num) {
		this.position = position;
		this.roomnum = num;
		switch(roomnum) {
			case 1 : color = Color.decode("#F0F3EA"); break;
			case 2 : color = Color.decode("#F3DFF5"); break;
			case 3 : color = Color.decode("#F7CBCE"); break;
			case 4 : color = Color.decode("#F2F0C9"); break;
			case 5 : color = Color.decode("#C3D9E8"); break;
			case 6 : color = Color.decode("#EDE3AD"); break;
			case 7 : color = Color.decode("#E0EDC0"); break;
			case 8 : color = Color.decode("#D7C3E3"); break;
			case 9 : color = Color.decode("#E4BEE8"); break;
		}

		//MyUtils.Log("[RoomSquare] Created at ("+position.getX()+","+position.getY()+") with room number "+roomnum);
	}

	/**
	 * Get the number assigned to this square to define what room it is a part of
	 */
	public int getRoomNumber() {
		return this.roomnum;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Room getRoom() {
		return this.room;
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
	 * Return string reference to this
	 * @return string
	 */
	public String toString() {
		return "RoomSquare("+position.getX()+","+position.getY()+")";
	}

	/**
	 * Draws square on supplied graphics object, at defined square size.
	 * @param graphics object to draw on
	 * @param int size of square
	 * @param y Offset cause by menu bar above
	 */
	public void draw(Graphics g, int squareSize, int menuSize) {
		g.setColor(this.color);
		g.fillRect(position.getX() * squareSize, (position.getY() * squareSize), squareSize, squareSize);
	}
}
