package cluedo.cards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cluedo.MyUtils;
import cluedo.Position;
import cluedo.Room;


/**
 * Represents weapon cards.
 * @author myles
 *
 */
public class WeaponCard implements Card {
	private String name;
	private Image image;
	private Image icon;
	private Room room;
	private Position position;

	public WeaponCard(String name) {
		this.name = name;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Room getRoom() {
		return room;
	}

	public void setPosition(Position pos) {
		this.position = pos;
	}

	public Position getPosition() {
		return position;
	}

	/**
	 * Get the name of this weapon
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
	 * Add Icon to card
	 */
	public void addIcon(String path) {
		try {
			this.icon = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			MyUtils.End("Invalid Image Path, Fool");
		}
	}

	/**
	 * Draws card on Player Panel graphics component
	 */
	public void draw(Graphics g, int x, int y, int width, int height) {
		g.drawImage(this.image,  x,  y,  width, height, null);
		//g.setColor(Color.WHITE);
		//g.fillRect(x, y, width, height);
		//g.setColor(Color.BLACK);
		//g.drawRect(x, y, width, height);
		//g.drawString(this.name, x + 20, y + 20);
	}

	/**
	 * Draws icon on Board Panel graphics component
	 */
	public void drawIcon(Graphics g, int x, int y, int width, int height) {
		g.drawImage(this.icon,  x,  y,  width, width, null);
		//g.setColor(Color.WHITE);
		//g.fillRect(x, y, width, height);
		//g.setColor(Color.BLACK);
		//g.drawRect(x, y, width, height);
		//g.drawString(this.name, x + 20, y + 20);
	}
}
