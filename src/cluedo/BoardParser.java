package cluedo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cluedo.squares.*;

/**
 * Board Parser is made to load a text file into the game and create a level out of it.
 * This should hopefully make changing anything to do with the board easy to change and edit
 *
 * The file must be formatted as follows:
 * 		-The first two lines are integers that refer to the width and height of the board in squares (25 means 25 squares wide)
 * 		-The following however many lines create a 2d array of the board, using the following key:
 * 			KEY:
 *				H = HallwaySquare
 *				R = RoomSquare
 *				N = NonSquare
 *				D = DoorSquare
 *				T = TunnelSquare
 *				X = Terminate
 *		-The X character must be used after the last square character, otherwise bad things WILL happen.
 * @author myles
 *
 */
public class BoardParser {
	private Board board;

	public BoardParser() {

	}


	/**
	 * Parses supplied txt file, and creates new game board from it.
	 * @param board file to be read, properly formatted
	 * @return Fresh new board, ready for playing
	 */
	public Board parseBoard(File file) {
		try {
			MyUtils.Log("[BoardParser] Parsing Board!");
			Scanner scanner = new Scanner(file);
			board = new Board(scanner.nextInt(), scanner.nextInt());

			int i = 0;
			String strLine = scanner.nextLine();
			MyUtils.Log("[BoardParser] Reading File and Constructing Squares");
			// While there is a next input
			while(scanner.hasNext()){
				strLine = scanner.nextLine();

				// End if X
				if(strLine.charAt(0) == 'X') break;

				MyUtils.Log("[BoardParser] "+strLine);

				// For each character in line, decide type of square, construct it, and add it to board.
				for(int j = 0; j < strLine.length(); j++) {
					board.addSquare(parseCharacter(strLine.substring(j, j+1), new Position(j,i)));
				}

				i++;
			}
			MyUtils.Log("[BoardParser] Finished! Board Created.");
			scanner.close();

			return board;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Parses what type of Square a supplied character refers to, returns new square of that type
	 * @param Character to be checked
	 * @param Position new square element is to be added to
	 * @return New Square, with correct position.
	 */
	private Square parseCharacter(String squareType, Position position) {
		//MyUtils.Log(squareType);
		if(squareType.equals("H")) return new HallSquare(position);
		if(squareType.equals("T")) return new TunnelSquare(position);
		if(squareType.equals("N")) return new NonSquare(position);
		if(squareType.equals("D")) return new DoorSquare(position);
		if(squareType.equals("S")) return new SpawnSquare(position);
		return new RoomSquare(position, Integer.parseInt(squareType));

	}
}
