package cluedo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cluedo.squares.*;

public class BoardParser {
	private Board board;
	
	public BoardParser() {
		
	}
	
	
	/**
	 * TODO: Exceptions
	 * @param file
	 * @return
	 */
	public Board parseBoard(File file) {
		try {
			Scanner scanner = new Scanner(file);
			board = new Board(scanner.nextInt(), scanner.nextInt());
			
			int i = 0;
			char c;
			String str = scanner.nextLine();
			while(scanner.hasNext()){
				str = scanner.nextLine();
				System.out.println("str: "+str+ " length: "+str.length());
				if(str.charAt(0) == 'X') break;
				
				for(int j = 0; j < str.length(); j++) {
					board.addSquare(parseCharacter(str.charAt(j), new Position(j,i)));
				}
				
				i++;
			}
				
			scanner.close();
			
			return board;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Square parseCharacter(char type, Position position) {
		if(type == 'H') return new HallSquare(position);
		if(type == 'T') return new TunnelSquare(position);
		if(type == 'R') return new RoomSquare(position);
		if(type == 'N') return new NonSquare(position);
		if(type == 'D') return new DoorSquare(position);
		return null;
	}
	

}
