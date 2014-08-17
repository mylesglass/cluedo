package cluedo;

import java.util.ArrayList;

import cluedo.cards.RoomCard;
import cluedo.squares.RoomSquare;

/**
 * Represents a Room in its entirety, contains the set of RoomSquares
 * contained in the room along with the RoomCard associated with said
 * room.
 *
 *
 * @author Neal Hartley & Myles Glass
 *
 */

public class Room {

	private String name;
	private RoomCard room;
	private ArrayList<RoomSquare> roomsquares;

	public Room(RoomCard c){
		this.name= c.getName();
		room = c;
		this.roomsquares = new ArrayList<RoomSquare>();
	}
	/**
	 * add a single square to the set of squares in the room.
	 */
	public void addSquare( RoomSquare square){
		this.roomsquares.add(square);

	}
	/**
	 * maps the set of room squares contained in a sepicified room to
	 * the room.
	 */
	public ArrayList<RoomSquare> getSquares(){
		return this.roomsquares;
	}
	/**
	 * Get the name of this room
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Get the card associated with this room
	 * @return RoomCard
	 */
	public RoomCard getRoom(){
		return this.room;

	}
}
