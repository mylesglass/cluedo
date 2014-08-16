package cluedo.cards;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Room Card
 * xxx
 * @author myles && Neal
 *
 */
public class RoomCard implements Card {
	private String name;
	//private Room room;

	//public RoomCard(String name, Room room) {
	public RoomCard(String name) {
		this.name = name;
		//this.room = room;
	}

	/**
	 * Get the name of the room card
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the room this card is linked to
	 * @return room
	 */
	//public Room getRoom() {
	//	return room;
	//}

	/**
	 * Draws card on Player Panel graphics component
	 */
	public void draw(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawString(this.name, x + 20, y + 20);
	}
}
