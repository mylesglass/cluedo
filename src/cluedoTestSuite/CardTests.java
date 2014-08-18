package cluedoTestSuite;

import static org.junit.Assert.*;

import org.junit.Test;

import cluedo.Position;
import cluedo.Room;
import cluedo.cards.CharacterCard;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;

public class CardTests {

	private RoomCard r = new RoomCard("room");
	private WeaponCard w = new WeaponCard("weapon");
	private CharacterCard c = new CharacterCard("character");
	
	private Position pos = new Position(1,1);
	private Room testSuite = new Room(r);
	
	//weaponCard tests.
	
	@Test public void testSetGetRoom(){
		w.setRoom(testSuite);
		assertTrue(w.getRoom()==testSuite);
	}
	@Test public void testSetGetPos(){
		w.setPosition(pos);
		assertTrue(w.getPosition()==pos);
	}
	@Test public void testGetName(){
		assertTrue(w.getName().equals("weapon"));
	}

	
	
	
	

}
	

