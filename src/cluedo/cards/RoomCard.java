package cluedo.cards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cluedo.MyUtils;

/**
 * Room Card
 * xxx
 * @author myles && Neal
 *
 */
public class RoomCard implements Card {
	private String name;
	private Image image;
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
	 * Add Image to card
	 */
	public void addImage(String path) {
		try {
			this.image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			MyUtils.End("Invalid Image Path, Fool");
		}
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
		//g.setColor(Color.WHITE);
		g.drawImage(this.image, x, y, width, height, null);
		//g.setColor(Color.BLACK);
		//g.drawRect(x, y, width, height);
		//g.drawString(this.name, x + 20, y + 20);
	}
}
