package cluedo.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import cluedo.Board;
import cluedo.BoardParser;


public class BoardTest {

	@Test
	public void testInvalidBoard() {
		BoardParser bp = new BoardParser();
		Board board = bp.parseBoard(new File("src/tests/badboard1.txt"));
		
		fail(board.initBoard());
		
	}
}
