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
 * Tunnel Squares connect the corner rooms together.
 * @author myles
 *
 */
public class TunnelSquare implements Square {
	private Position position;
	private Color color;
	private TunnelSquare pairSquare;
	private Room room;
	private Image image;

	public TunnelSquare(Position position) {
		this.position = position;
		this.color = Color.MAGENTA;

		try {
			this.image = ImageIO.read(new File("src/images/squares/square-tunnel.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Add the Tunnel Square that this square links to
	 * @param TunnelSquare
	 *
	 */
	public void addPair(TunnelSquare pair) {
		if(this.pairSquare != null) {
			System.out.printf("Attempting to give tunnel at (%d,%d) a pair, but it already has one!", position.getX(), position.getY());
		}
		else {
			this.pairSquare = pair;
		}
	}

	/**
	 * Get the paired square of this tunnel
	 */
	public TunnelSquare getPair() {
		return pairSquare;
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
	 * Set the room that this tunnel square is contained in
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * Get the room this tunnel square is in
	 * @return
	 */
	public Room getRoom() {
		return this.room;
	}

	/**
	 * Print out char representing type of square. For testing purposes.
	 */
	public void print() {
		System.out.print('T');
	}

	/**
	 * Return string reference to this
	 * @return string
	 */
	public String toString() {
		return "SuperMagicalTunnelSquare("+position.getX()+","+position.getY()+")";
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
