package cluedo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import cluedo.cards.Card;

public class PlayerPanel extends JPanel {
	private int width;
	private int height;
	private Player currentPlayer;
	private boolean hasPlayer = false;
	private final int CARD_X_POS = 80;
	private final int CARD_Y_POS = 100;
	private final int CARD_WIDTH = 132;
	private final int CARD_HEIGHT = 200;
	private final int CARD_SPACING = 10;


	public PlayerPanel(int width, int height) {
		this.width = width;
		this.height = height;


		MyUtils.Log("[PlayerPanel] Player Panel Created. Size: "+width+", "+height);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Player Panel", 100, 100);
		if(hasPlayer) {
			drawPlayerCards(g);
			MyUtils.Log("[PlayerPanel] Drawing "+currentPlayer.getName()+"'s cards on panel.");
		}
	}

	private void drawPlayerCards(Graphics g) {
		ArrayList<Card> cards = currentPlayer.getHand();
		int count = 1;
		for(Card c : cards) {
			c.draw(g, (CARD_X_POS * count) - (CARD_SPACING * (count - 1)), CARD_Y_POS, CARD_WIDTH, CARD_HEIGHT);
			count++;
		}
	}

	public void setCurrentPlayer(Player p) {
		this.currentPlayer = p;
		this.hasPlayer = true;
	}
}
