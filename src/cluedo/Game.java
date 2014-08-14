package cluedo;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import cluedo.cards.Card;
import cluedo.cards.CharacterCard;

/**
 * Game holds all of the logic of Cluedo.
 * It is also the main class that directs all other components.
 * @author myles
 *
 */

public class Game {
	private GUI gui;
	private Board board;
    private ArrayList<Player> players;
    private ArrayList<Card> deck;

	public Game() {
		MyUtils.Log("===================");
		MyUtils.Log("---    Cluedo    ---");
		gui = new GUI(); // Construct GUI
		MyUtils.Log("[Game] GUI Constructed.");

		// Construct board from text file
		BoardParser boardparse = new BoardParser();
		board = boardparse.parseBoard(new File("src/board.txt"));
		MyUtils.Log("[Game] Board Parsed and Constructed.");

		// construct a set of cards from text file
		CardParser cardParser = new CardParser();
		cardParser.parseCards(new File("src/cards.txt"));
		this.deck = cardParser.getDeck();
		MyUtils.Log("[Game] Deck Parsed and Constructed.");

		//construct players from cards.
		players = new ArrayList<Player>();
		for(Card c: deck){
			if(c instanceof CharacterCard){

				CharacterCard card = (CharacterCard)c ;

                Player player = new Player(card);

                players.add(player);

				MyUtils.Log("[Game] constructing players from deck: " + c.getName());

			}
		}

		// Set up initial board.
		gui.initialiseGameInterface(board.getWidth(), board.getHeight());
		gui.updateBoard(board);
		MyUtils.Log("[Game] GUI dimensions set and board supplied.");

		redraw();
	}

	/**
	 * Get board
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Redraw GUI
	 */
	private void redraw() {
		gui.draw();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Game game = new Game();
	}
}
