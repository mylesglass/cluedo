package cluedo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private final int SQUARE_SIZE = 20;
	private int width;
	private int height;
	private Board board;
	private ArrayList<Player> players;
	private boolean hasPlayers = false;


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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		board.draw(g, SQUARE_SIZE, 0);
		if(hasPlayers) drawPlayers(g);

	}


	private void drawPlayers(Graphics g) {
		for(Player p : players) {
			p.draw(g, SQUARE_SIZE);
			MyUtils.Log("[BoardPanel] Drawing player "+p.getName());
		}
	}
}
