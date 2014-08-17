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
	private ArrayList<SpawnSquare> spawns;

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
		this.spawns = new ArrayList<SpawnSquare>();
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
		if(square instanceof SpawnSquare) {
			spawns.add((SpawnSquare) square);
		}
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
	 * Initialize Each Spawn Square with a player.
	 * The order is defined by the order in which they are parsed in card parser
	 */
	public void initialiseSpawns(ArrayList<Player> players) {
		for(int i = 0; i < players.size(); i++) {
			players.get(i).setPos(spawns.get(i).getPosition());
			MyUtils.Log("[Board] Spawning "+players.get(i).getName() +" at position "+spawns.get(i).getPosition().toString());
		}
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
					((RoomSquare)board[w][h]).setRoom(rooms.get(roomnum));
				}
			}
		}

		return rooms;
	}

	public void addRoomToDoors() {
		for(int h = 0; h < this.height; h++) {
			for(int w = 0; w < this.width; w++) {
				if(board[w][h] instanceof RoomSquare) {
					findRoom(w, h);
				}
			}
		}
	}

	private void findRoom(int w, int h) {
		if(board[w -1][h] instanceof RoomSquare) {
			((DoorSquare)board[w][h]).setRoom(((RoomSquare)board[w][h]).getRoom());
		}
		else if(board[w + 1][h] instanceof RoomSquare) {
			((DoorSquare)board[w][h]).setRoom(((RoomSquare)board[w][h]).getRoom());
		}
		else if(board[w][h + 1] instanceof RoomSquare) {
			((DoorSquare)board[w][h]).setRoom(((RoomSquare)board[w][h]).getRoom());
		}
		else if(board[w][h - 1] instanceof RoomSquare) {
			((DoorSquare)board[w][h]).setRoom(((RoomSquare)board[w][h]).getRoom());
		}
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
