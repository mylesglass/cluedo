package cluedo.cards;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a character card in the game cluedo.
 * @author myles
 *
 */
public class CharacterCard implements Card {
	private String name;

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
	 * Draws card on Player Panel graphics component
	 */
	public void draw(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawString(this.name, x + 20, y + 20);
	}

}
