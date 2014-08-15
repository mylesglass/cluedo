package cluedo;

import java.awt.Graphics;
import java.util.ArrayList;

import cluedo.squares.*;
import cluedo.exceptions.*;

/**
 * Responsible for holding all information regarding the current state of the board.
 * @author myles
 */
public class Board {
	private int width;
	private int height;
	private Square[][] board;

	/**
	 * Construct board object, defined by width and height in squares
	 * @param width in amount of squares
	 * @param height in amount of squares
	 */
	public Board(int width, int height) {
		MyUtils.Log("[Board] Creating board with width: "+width+" height: "+height);
		this.width = width;
		this.height = height;
		this.board = new Square[width][height];
	}

	/**
	 * Initialises board for playing use.
	 * 	-Sets adjacent hall squares for each door
	 * 	-Sets doors to rooms
	 * 	-Assigns Spawn points to each character/player
	 */
	public void initBoard() {
		try {
			addDoors();
		}
		catch(InvalidBoardException e) {
			e.printStackTrace();
			MyUtils.End("Invalid Board! Follow the rules son!");
		}
		catch(Exception e) {
			e.printStackTrace();
			MyUtils.End("Fatal Error! I don't know how this happened!");
		}
	}

	/**
	 * Add Square to Board at the squares contained position
	 * @param square to add
	 */
	public void addSquare(Square square){
		Position sqpos = square.getPosition();
		board[sqpos.getX()][sqpos.getY()] = square;
	}

	/**
	 * Get Square at supplied x and y coordinates
	 * @param x position
	 * @param y position
	 * @return Square at (x,y)
	 */
	public Square getSquareAt(int x, int y) {
		return board[x][y];
	}

	/**
	 * Get width of board
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Get height of board
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Draws the board on supplied graphics objects.
	 * @param graphics object to draw on
	 * @param square size
	 * @param menu bar size
	 */
	public void draw(Graphics g, int squareSize, int menuSize) {
		for(int i = 0; i < this.getHeight(); i++) {
			for(int j = 0; j < this.getWidth(); j++) {
				this.getSquareAt(j, i).draw(g, squareSize, menuSize);
			}
		}
	}

	public ArrayList<Room> addSquaresToRooms(ArrayList<Room> rooms) {
		for(int h = 0; h < this.height; h++) {
			for(int w = 0; w < this.width; w++) {
				if(board[w][h] instanceof RoomSquare) {
					int roomnum = ((RoomSquare)board[w][h]).getRoomNumber() -1;
					rooms.get(roomnum).addSquare((RoomSquare)board[w][h]);
				}
			}
		}

		return rooms;
	}


	/**
	 * Creates links between doors and the hall squares they are linked to.
	 *
	 */
	private void addDoors() throws InvalidBoardException {
		int doorCount = 0;
		// Loop through each column
		for(int j = 0; j < this.height; j++) {
			//Loop through each row
			for(int i = 0; i < this.width; i++) {
				if(board[i][j] instanceof DoorSquare) {
					doorCount++;
					// Link to Hallsquare, throw exception if board is constructed wrong
					// West
					if(board[i][j-1] instanceof HallSquare) {
						((DoorSquare)board[i][j]).setHallSquare((HallSquare) board[i][j-1]);
						((HallSquare) board[i][j-1]).setDoorNear(true);
						continue;
					}
					// East
					else if(board[i][j+1] instanceof HallSquare) {
						((DoorSquare)board[i][j]).setHallSquare((HallSquare) board[i-1][j+1]);
						((HallSquare) board[i][j+1]).setDoorNear(true);
						continue;
					}
					// South
					else if(board[i-1][j] instanceof HallSquare) {
						((DoorSquare)board[i][j]).setHallSquare((HallSquare) board[i-1][j]);
						((HallSquare) board[i-1][j]).setDoorNear(true);
						continue;
					}
					// North
					else if(board[i+1][j] instanceof HallSquare) {
						((DoorSquare)board[i][j]).setHallSquare((HallSquare) board[i+1][j]);
						((HallSquare) board[i+1][j]).setDoorNear(true);
						continue;
					}

					throw new InvalidBoardException("[Invalid Board] There must be a hallway square adjacent to a door square!");
				}
			}
		}
		MyUtils.Log("[Board] "+doorCount+" Doors successfully created and connected to Hallways.");
	}



}
