package cluedoTestSuite;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import cluedo.CardParser;
import cluedo.Game;
import cluedo.Position;

public class PositionTests {
	
	Position p = new Position(1, 1);
	
	@Test public void testgetX(){
	 Position p = new Position(1, 1);
	 assertTrue(p.getX()==1);
	}
	
	@Test public void testgetXFalse(){
		 Position p = new Position(1, 1);
		 assertFalse(p.getX()==2);
		}
	
	
	@Test public void testgetY(){
		 Position p = new Position(1, 1);
		 assertTrue(p.getY()==1);
		}
	
	@Test public void testgetYFalse(){
		 Position p = new Position(1, 1);
		 assertFalse(p.getY()==2);
		}
	
	@Test public void testtoString(){
		 Position p = new Position(1, 1);
		 assertTrue(p.toString().equals("(1,1"));
		}
	@Test public void testtoStringFalse(){
		 Position p = new Position(1, 1);
		 assertFalse(p.toString().equals("afsasdgas"));
		}

	
}
