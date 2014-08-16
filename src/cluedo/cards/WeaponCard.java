package cluedo.cards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cluedo.MyUtils;


/**
 * Represents weapon cards.
 * @author myles
 *
 */
public class WeaponCard implements Card {
	private String name;
	private Image image;

	public WeaponCard(String name) {
		this.name = name;
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
}
