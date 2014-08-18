package cluedo.cards;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cluedo.MyUtils;

/**
 * Represents a character card in the game cluedo.
 * @author myles
 *
 */
public class CharacterCard implements Card {
	private String name;
	private Image image;
	private Image icon;

	public CharacterCard(String name) {
		this.name = name;
	}

	/**
	 * Get the name of this character card
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
	 * Add Player Icon Image to card
	 */
	public void addPlayerIcon(String path) {
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
		g.drawImage(this.image, x, y, width, height, null);
	}

	/**
	 * Draws card on Player Panel graphics component
	 */
	public void drawIcon(Graphics g, int x, int y, int width, int height) {
		g.drawImage(this.icon, x, y, width, height, null);
	}

}
