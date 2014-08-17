package cluedo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import cluedo.cards.WeaponCard;

public class BoardPanel extends JPanel {
	private final int SQUARE_SIZE = 20;
	private int width;
	private int height;
	private Board board;
	private ArrayList<Player> players;
	private ArrayList<WeaponCard> weapons;
	private boolean hasPlayers = false;
	private boolean hasWeapons = false;

	public BoardPanel(Board board) {
		this.board = board;
		this.width = board.getWidth() * SQUARE_SIZE;
		this.height = board.getHeight() * SQUARE_SIZE;
		MyUtils.Log("[BoardPanel] Board Panel Created. Size: "+this.width+", "+this.height);
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		this.hasPlayers = true;
	}

	public void updateWeapons(ArrayList<WeaponCard> weapons) {
		this.weapons = weapons;
		this.hasWeapons = true;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		board.draw(g, SQUARE_SIZE, 0);
		if(hasWeapons) drawWeapons(g);
		if(hasPlayers) drawPlayers(g);

	}

	private void drawWeapons(Graphics g) {
		for(WeaponCard wc : weapons) {
			//Position roomPos = wc.getRoom().getRandPos();
			wc.draw(g, wc.getPosition().getX() * SQUARE_SIZE, wc.getPosition().getY() * SQUARE_SIZE, SQUARE_SIZE * 2, SQUARE_SIZE * 3);
		}
	}


	private void drawPlayers(Graphics g) {
		for(Player p : players) {
			p.draw(g, SQUARE_SIZE);
		}
	}
}
