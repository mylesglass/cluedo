package cluedo.cards;

/**
 * Room Card
 * xxx
 * @author myles
 *
 */
public class RoomCard implements Card {
	private String name;
	//private Room room;

	//public RoomCard(String name, Room room) {
	public RoomCard(String name) {
		this.name = name;
		//this.room = room;
	}

	/**
	 * Get the name of the room card
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the room this card is linked to
	 * @return room
	 */
	//public Room getRoom() {
	//	return room;
	//}
}
