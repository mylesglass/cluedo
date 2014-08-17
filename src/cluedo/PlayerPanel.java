package cluedo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
	private Font usernameFont = new Font("Dialog", Font.BOLD, 36);
	private Font characterFont = new Font("Dialog", Font.PLAIN, 30);

	private int stepsRemaining;


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
		if(hasPlayer) {
			// Write Names
			g.setFont(usernameFont);
			g.setColor(Color.WHITE);
			g.drawString(currentPlayer.getUsername(), CARD_X_POS, 40);
			g.setFont(characterFont);
			g.drawString(currentPlayer.getName(), CARD_X_POS, 75);


			//Draw Roll
			g.drawString(stepsRemaining+" steps remaining", 450, 75);

			// Draw cards
			drawPlayerCards(g);
			MyUtils.Log("[PlayerPanel] Drawing "+currentPlayer.getName()+"'s cards on panel.");
		}
	}

	public void setStepsRemaining(int steps) {
		this.stepsRemaining = steps;
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
