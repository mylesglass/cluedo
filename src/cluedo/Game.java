package cluedo;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.RoomCard;

/**
 * Game holds all of the logic of Cluedo.
 * It is also the main class that directs all other components.
 * @author myles
 *
 */

public class Game {
	private GUI gui;
	private Board board;
	private ArrayList<Player> characters;
	private ArrayList<Player> players;
	private ArrayList<Card> deck;
	private ArrayList<Room> rooms;

	private ArrayList<String> roomNames;
	private ArrayList<String> characterNames;
	private ArrayList<String> weaponNames;

	public Game() {
		MyUtils.PrintLogo();

		gui = new GUI(); // Construct GUI
		MyUtils.Log("[Game] GUI Constructed.");
		gui.setState("MENU");

		// construct a set of cards from text file
		CardParser cardParser = new CardParser();
		cardParser.parseCards(new File("src/cards.txt"));
		this.deck = cardParser.getDeck();
		gui.updateCardNames(cardParser.getRoomNames(), cardParser.getCharacterNames(), cardParser.getWeaponNames());
		MyUtils.Log("[Game] Deck Parsed and Constructed.");

		//construct players from cards.
		characters = new ArrayList<Player>();
		for(Card c: deck){
			if(c instanceof CharacterCard){
				CharacterCard card = (CharacterCard)c ;
				Player player = new Player(card);
				characters.add(player);
				MyUtils.Log("[Game] constructing players from deck: " + c.getName());

			}
		}
		// Add colors to each player
		int i = 0;
		for(String str : cardParser.getCharacterColors()) {
			characters.get(i).setColor(str);
			MyUtils.Log("[Game] "+characters.get(i).getName()+" is color "+str);
			i++;
		}

		// Construct Rooms from Cards, and assign roomSquares to them
		rooms = new ArrayList<Room>();
		for(Card c : deck) {
			if(c instanceof RoomCard) {
				rooms.add(new Room((RoomCard)c));
				MyUtils.Log("[Game] Constructed Room " + c.getName() + "  from deck.");
			}
		}

		// Construct board from text file
		BoardParser boardparse = new BoardParser();
		board = boardparse.parseBoard(new File("src/boards/newboard.txt"));
		board.initBoard();
		rooms = board.addSquaresToRooms(rooms);
		board.initialiseSpawns(characters);
		MyUtils.Log("[Game] Board Parsed and Constructed.");

		// Set up initial board.
		gui.initialiseGameInterface(board);
		MyUtils.Log("[Game] GUI dimensions set and board supplied.");



		// Start GUI on Menu
		gui.drawGame();

		gui.drawPlayersToBoard(characters);
	}

	/**
	 * Get board
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Rolls one die and return an integer in the range of 1 to 6.
	 * Displays rolling graphic in GUI.
	 * @return random int
	 */
	private int rollDice() {
		int random = (int) (1 + Math.random() * 6);
		MyUtils.Log("[Game] User has rolled a: "+random);
		//	gui.roll(random);
		return random;
	}

	private void initialisePlayers() {
		MyUtils.Log("fuck you buddy");
		// Initialise Each Player
		for(String name : gui.getPlayers()) {
			MyUtils.Log(name);
			for(Player p : characters) {
				if(p.getName().equals(name)) {
					players.add(p);
					MyUtils.Log("[Game] "+p.getName()+" is ready for detective work");
				}
			}
		}
	}

	private void initialisePlayer(Player player) {
		// Need to create players checklist before dealing cards
		player.addChecklist(new Checklist(gui.roomNames, gui.characterNames, gui.weaponNames));
		// deal cards

		// Create Tokens

		// Place on Spawn Points

		// Draw on board
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Game game = new Game();
	}
}
