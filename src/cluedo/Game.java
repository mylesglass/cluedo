package cluedo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import cluedo.cards.Accusation;
import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;
import cluedo.squares.DoorSquare;

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
	private Accusation winningCombo;



	private ArrayList<String> roomNames;
	private ArrayList<String> characterNames;
	private ArrayList<String> weaponNames;

	private ArrayList<WeaponCard> weapons;

	/**
	 * holds all of the main functionality for game initialisation and then play,
	 *  Parses cards using CardParser.
	 *  Uses card parses to construct a deck, and to construct all characters.
	 *  sets up game with selected players.
	 */
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
		weapons = new ArrayList<WeaponCard>();
		for(Card c : deck) {
			if(c instanceof RoomCard) {
				rooms.add(new Room((RoomCard)c));
				MyUtils.Log("[Game] Constructed Room " + c.getName() + "  from deck.");
			}

			if(c instanceof WeaponCard) {
				weapons.add((WeaponCard)c);
			}
		}

		i = 0;

		Collections.shuffle(weapons);
		for(WeaponCard wc : weapons) {
			wc.setRoom(rooms.get(i));
			i++;
		}

		gui.setWeapons(weapons);

		// Construct board from text file
		BoardParser boardparse = new BoardParser();
		board = boardparse.parseBoard(new File("src/boards/newboard.txt"));
		board.initBoard();
		rooms = board.addSquaresToRooms(rooms);
		board.addRoomToDoors();
		board.addRoomToTunnels();
		board.linkTunnels();
		board.initialiseSpawns(characters);
		MyUtils.Log("[Game] Board Parsed and Constructed.");

		// Set up initial board.
		gui.initialiseGameInterface(board);
		MyUtils.Log("[Game] GUI dimensions set and board supplied.");



		// Start GUI on Menu
		gui.drawGame();



		//FIXME only works if printing infinite loop??
		while(true){
			MyUtils.Pause(100);
			if(gui.isReady()){
				break;
			}


		}

		MyUtils.Log("[Game] made it passed isReady!.");

		players = new ArrayList<Player>();

		initialisePlayers();

		for(int k = 0; k < players.size(); k++){
			initialisePlayer(players.get(k));
			players.get(k).setUsername(gui.getUsernames().get(k));
		}

		gui.drawPlayersToBoard(players);

		i = 0;
		for(WeaponCard wc : weapons) {
			wc.setPosition(rooms.get(i).getRandPos());
			i++;
		}
		gui.drawWeaponsToBoard(weapons);

		gui.drawRoomNames(rooms);

		dealCards();
		i=0;
		while(true){
			gui.setCurrentPlayer(players.get(i));
			int steps = rollDice();



			if(players.get(i).getSquare().equals("D")){

				DoorSquare door = (DoorSquare)board.getSquareAt(players.get(i).getPos().getX(),players.get(i).getPos().getY());
				Boolean b = (board.getSquareAt(players.get(i).getPos().getX(),players.get(i).getPos().getY()) instanceof DoorSquare);

				Room thisRoom = door.getRoom();

				Position pos = thisRoom.getRandPos();



				players.get(i).setPos(pos);
				players.get(i).setSquare("R");




			}

			if(players.get(i).getSquare().equals("R")){
				gui.takeRoomTurn();
			}

			if(players.get(i).getSquare().equals("H")){
				gui.takeTurn(steps);
			}



			if(i==players.size()-1){
				i=0;
			}
			else{i++;}

			if(gui.hasGameFinished()) {
				break;
			}
		}

		MyUtils.Log("[GAME] you won!????");
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
		return random;
	}


	/**
	 * initialises all players based on the gui's list of players who are active i.e.
	 * controlled in this game. whittles down the lsit of potential characters to actual players.
	 */
	private void initialisePlayers() {
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

	/**
	 * initialises a a single player. sets up their checklist.
	 * @param player
	 */
	private void initialisePlayer(Player player) {
		// Need to create players checklist before dealing cards
		player.addChecklist(new Checklist(gui.roomNames, gui.characterNames, gui.weaponNames));
		// deal cards
		this.gui.initialiseCheckPanel();


		gui.drawGame();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Game game = new Game();
	}

	/**
	 * deals cards to all players. also sets up the winningCombo, or the 3 cards that reveal the murder.
	 */
	private void dealCards(){

		Collections.shuffle(deck);
		CharacterCard charac = null;
		RoomCard room = null;
		WeaponCard weap = null;


		for(Card c: deck){
			if(c instanceof CharacterCard){ charac= (CharacterCard) c;}
			if(c instanceof WeaponCard){ weap= (WeaponCard) c;}
			if(c instanceof RoomCard){ room = (RoomCard) c;}
		}
		deck.remove(charac);
		deck.remove(weap);
		deck.remove(room);

		winningCombo = new Accusation(charac.getName(), room.getName(), weap.getName());
		gui.setWinner(winningCombo);
		int c = 0;
		int i= 0;
		int j=0;
		while(!deck.isEmpty()){
			MyUtils.Log("[Game] "+ players.get(i).getName() + " got card: "+ deck.get(j).getName());
			players.get(i).addCard(deck.get(j));
			deck.remove(j);

			c++;
			if(i == players.size()-1){
				i=0;
			}
			else{i++;}
		}
	}

	
}
