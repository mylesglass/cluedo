package cluedo.cards;

import java.awt.Graphics;

/**
 * Interface for all cards in the game.
 * @author myles
 *
 */
public interface Card {

	public String getName();
	public void draw(Graphics g, int x, int y, int width, int height);

}
