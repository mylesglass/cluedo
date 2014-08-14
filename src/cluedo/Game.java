package cluedo;

import java.io.File;

/**
 * Game holds all of the logic of Cluedo.
 * It is also the main class that directs all other components.
 * @author myles
 *
 */

public class Game {
	private GUI gui;
	private Board board;

	public Game() {
		MyUtils.Log("===================");
		MyUtils.Log("---    Cluedo    ---");
		gui = new GUI(); // Construct GUI
		MyUtils.Log("[Game] GUI Constructed.");

		// Construct board from text file
		BoardParser boardparse = new BoardParser();
		board = boardparse.parseBoard(new File("src/board.txt"));
		MyUtils.Log("[Game] Board Parsed and Constructed.");

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
