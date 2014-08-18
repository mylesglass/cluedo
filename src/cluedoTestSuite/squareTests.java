package cluedoTestSuite;

import static org.junit.Assert.*;

import org.junit.Test;

import cluedo.Position;
import cluedo.Room;
import cluedo.cards.RoomCard;
import cluedo.squares.DoorSquare;
import cluedo.squares.HallSquare;
import cluedo.squares.RoomSquare;
import cluedo.squares.TunnelSquare;

public class squareTests {
 
	
	private Position  p= new Position(1, 1);
	private HallSquare h = new HallSquare(p);
	private RoomSquare r = new RoomSquare(p, 1);
	private DoorSquare d = new DoorSquare(p);
	private TunnelSquare t = new TunnelSquare(p);
	private TunnelSquare t2 = new TunnelSquare(p);
	
	
	Room testSuite = new Room(new RoomCard("room"));
	
	//hallSquare tests.
	@Test public void testHallGetPos(){
		assertTrue(h.getPosition()==p);
	}
	@Test public void testHallSetDoor(){
		h.setDoorNear(true);
		assertTrue(h.doorNear());
		h.setDoorNear(false);
		assertFalse(h.doorNear());
	}
	
	//RoomSquare tests.
	@Test public void testRoomGetNum(){
		assertTrue(r.getRoomNumber()==1);
		assertFalse(r.getRoomNumber()!=1);
		
	}
	@Test public void testGetRoomNull(){
		assertTrue(r.getRoom()==null);
		assertFalse(r.getRoom()!=null);
		
	}
	@Test public void testRoomSetAndGet(){
		
		r.setRoom(testSuite);
		assertTrue(r.getRoom()==testSuite);
		
	}
	@Test public void testRoomPos(){
		assertTrue(r.getPosition()==p);
		
	}
	
	//DoorSquareTests
	
	@Test public void testDoorSquareGetSetRoom(){
		d.setRoom(testSuite);
		assertTrue(d.getRoom()==testSuite);
		
	}
	@Test public void testDoorSquareGetPos(){
		assertTrue(d.getPosition().equals(p));
	}
	
	@Test public void testDoorSquareHallSquareSet(){
		d.setHallSquare(h);
		
	}
	
	//testTunnelSquares
	
	@Test public void testTunnels(){
		t.addPair(t2);
		assertTrue(t.getPair()==t2);
	}
	@Test public void TestGetPos(){
		
		assertTrue(t.getPosition()==p);
	}
	@Test public void testSetRoom(){
		t.setRoom(testSuite);
		assertTrue(t.getRoom()==testSuite);
	}
}
