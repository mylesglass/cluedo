package cluedo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFrame;

import cluedo.squares.Square;

/**
 * Responsible for displaying any information that the user needs to play the game.
 * @author myles
 *
 */
public class GUI {
	private int width = 390;
	private int height = 430;
	private JFrame frame;
	private JComponent drawing;
	private Board board;

	public GUI() {
		setupInterface();
		BoardParser boardparse = new BoardParser();
		board = boardparse.parseBoard(new File("src/board.txt"));
		drawing.repaint();
	}

	private void setupInterface() {
		frame = new JFrame("Cluedo 2014 XXXL GOTY Edition");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);

		drawing = new JComponent() {
			protected void paintComponent(Graphics g){
				redraw(g);
			}
		};

		frame.add(drawing, BorderLayout.CENTER);

		frame.setVisible(true);

	}

	private void redraw(Graphics g) {
		for(int i = 0; i < board.getHeight(); i++) {
			for(int j = 0; j < board.getWidth(); j++) {
				Square current = board.getSquareAt(j, i);
				g.setColor(current.getColor());
				g.fillRect(j * 15, i * 15, 15, 15);
				g.setColor(Color.BLACK);
				g.drawRect(j*15, i*15, 15, 15);
			}
		}
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
	}
}
