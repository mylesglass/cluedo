package cluedo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cluedo.cards.WeaponCard;
import cluedo.squares.RoomSquare;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private final int SQUARE_SIZE = 25;
	private int width;
	private int height;
	private Board board;
	private ArrayList<Player> players;
	private ArrayList<Room> rooms;
	private ArrayList<WeaponCard> weapons;
	private boolean hasPlayers = false;
	private boolean hasWeapons = false;
	private boolean hasRooms = false;
	private Image centerImage;

	public BoardPanel(Board board) {
		this.board = board;
		this.width = board.getWidth() * SQUARE_SIZE;
		this.height = board.getHeight() * SQUARE_SIZE;
		MyUtils.Log("[BoardPanel] Board Panel Created. Size: "+this.width+", "+this.height);

		try {
			this.centerImage = ImageIO.read(new File("src/images/center.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		this.hasPlayers = true;
	}

	public void updateWeapons(ArrayList<WeaponCard> weapons) {
		this.weapons = weapons;
		this.hasWeapons = true;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
		findFirstRoom();
		hasRooms = true;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);

		board.draw(g, SQUARE_SIZE, 0);
		g.drawImage(centerImage, 11 * SQUARE_SIZE, 11 * SQUARE_SIZE, 5 * SQUARE_SIZE, 7 * SQUARE_SIZE, null);
		if(hasWeapons) drawWeapons(g);
		if(hasPlayers) drawPlayers(g);
		if(hasRooms) drawRoomNames(g);
	}

	private void drawRoomNames(Graphics g) {
		MyUtils.Log("[BoardParser] Drawing Room Names to squares");
		for(RoomSquare rs : firsts) {
			g.setColor(Color.BLACK);
			g.drawString(rs.getRoom().getName(), (rs.getPosition().getX() * SQUARE_SIZE) + SQUARE_SIZE, (rs.getPosition().getY() * SQUARE_SIZE) + SQUARE_SIZE*2);
		}
	}

	private ArrayList<RoomSquare> firsts;

	private void findFirstRoom() {
		firsts = new ArrayList<RoomSquare>();

		for(Room r : rooms) {
			RoomSquare first = r.getSquares().get(0);
			for(RoomSquare rs : r.getSquares()) {
				if(rs.getPosition().getX() <= first.getPosition().getX()) {
					if(rs.getPosition().getY() <= first.getPosition().getY()) {
						first = rs;
					}
				}
			}
			firsts.add(first);
		}
	}

	private void drawWeapons(Graphics g) {
		for(WeaponCard wc : weapons) {
			//Position roomPos = wc.getRoom().getRandPos();
			wc.drawIcon(g, wc.getPosition().getX() * SQUARE_SIZE, wc.getPosition().getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
		}
	}


	private void drawPlayers(Graphics g) {
		for(Player p : players) {
			p.draw(g, SQUARE_SIZE);
		}
	}
}
