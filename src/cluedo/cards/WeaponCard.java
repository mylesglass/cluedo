package cluedo.cards;

import java.awt.Color;
import java.awt.Graphics;


/**
 * Represents weapon cards.
 * @author myles
 *
 */
public class WeaponCard implements Card {
	private String name;

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
	 * Draws card on Player Panel graphics component
	 */
	public void draw(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawString(this.name, x + 20, y + 20);
	}
}
