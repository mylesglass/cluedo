package cluedo.squares;

import java.awt.Color;
import java.awt.Graphics;

import cluedo.Position;

import cluedo.MyUtils;


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

	public RoomSquare(Position position, int num) {
		this.position = position;
		this.roomnum = num;
		switch(roomnum) {
			case 1 : color = Color.decode("#F0F3EA"); break;
			case 2 : color = Color.decode("#835788"); break;
			case 3 : color = Color.decode("#CF202D"); break;
			case 4 : color = Color.decode("#E1D71E"); break;
			case 5 : color = Color.decode("#3E88B9"); break;
			case 6 : color = Color.decode("#C4AC28"); break;
			case 7 : color = Color.decode("#617F1B"); break;
			case 8 : color = Color.decode("#4C3957"); break;
			case 9 : color = Color.decode("#835788"); break;
		}
		//MyUtils.Log("[RoomSquare] Created at ("+position.getX()+","+position.getY()+") with room number "+roomnum);
	}

	/**
	 * Get the number assigned to this square to define what room it is a part of
	 */
	public int getRoomNumber() {
		return this.roomnum;
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
	public void draw(Graphics g, int squareSize, int yOffset) {
		g.setColor(this.color);
		g.fillRect(position.getX() * squareSize, (position.getY() * squareSize) + yOffset, squareSize, squareSize);
	}
}
