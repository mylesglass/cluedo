package cluedo;

import java.awt.Graphics;

import cluedo.squares.*;

/**
 * Responsible for holding all information regarding the current state of the board.
 * I guess this is static? because nothing changes, unless we want to keep player positons in here too?
 * idk
 * @author myles
 *
 */
public class Board {
	private int width;
	private int height;
	private Square[][] board;
	
	public Board(int width, int height) {
		System.out.println("Creating board with width: "+width+" height: "+height);
		this.width = width;
		this.height = height;
		this.board = new Square[width][height];
	}
	
	public void addSquare(Square square){
		Position sqpos = square.getPosition();
		System.out.printf("Adding Square to position %d %d\n", sqpos.getX(), sqpos.getY());
		board[sqpos.getX()][sqpos.getY()] = square;
	}
	
	public void printBoard() {
		for(int i = 0; i < height; i++) {
			for( int j = 0; j < width; j++) {
				board[j][i].print();
			}
			System.out.print("\n");
		}
	}
	
	public Square getSquareAt(int x, int y) {
		return board[x][y];
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
