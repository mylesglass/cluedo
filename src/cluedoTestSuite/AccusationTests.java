package cluedoTestSuite;

import static org.junit.Assert.*;

import org.junit.Test;

import cluedo.cards.Accusation;

public class AccusationTests {

	String one= "one";
	String two = "two";
	String three = "three";
	Accusation a = new Accusation(one, two, three);
	Accusation b = new Accusation(one, two, three);
	Accusation c = new Accusation(one, two, "jungle!");
@Test public void testEquals(){
	assertTrue(a.equals(b));
	assertFalse(a.equals(c));
}

@Test public void testGetKiller(){
	
	assertTrue(a.getKiller().equals(one));
	
}
@Test public void testGetScene(){
	
	assertTrue(a.getScene().equals(two));
}
@Test public void testGetWeapon(){
	
	assertTrue(a.getWeapon().equals(three));
}


}
