package cluedoTestSuite;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import cluedo.Checklist;
import cluedo.Player;
import cluedo.cards.Card;
import cluedo.cards.CharacterCard;

/**
 *
 * @author hartleneal && Myles Glass
 *TJUnit tests for testing Player class.
 *
 */

public class playerTest {

	CharacterCard card = new CharacterCard("Harry Potter");
	ArrayList<String> characters = new ArrayList<String>();
	ArrayList<String> weapons = new ArrayList<String>();
	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<Card> hand = new ArrayList<Card>();


	/**
	 * tests that basic character creation creates a character with the same name
	 * as card given.
	 */
	@Test public void PlayerCreation(){
		Player harryPotter = new Player(card);
		assert(card.getName().equals(harryPotter.getName()));
	}

	/**
	 *
	 * tests that adding a card to a players hand will check it of thier checklist
	 * auromatically.
	 */

	@Test public void addCardTestUpdateCheckList(){

		Player harryPotter = new Player(card);

		characters.add("Harry Potter");
		weapons.add("deathstick");
		rooms.add("Chamber of Secrets");
        
		harryPotter.initialiseHand(hand);
        
		Checklist checkList = new Checklist(rooms,characters,weapons);
		harryPotter.addChecklist(checkList);
		harryPotter.addCard(card);

		HashMap<String, Boolean> test = checkList.checkList();
		assert(test.get(card.getName())==true);
	}

	@Test public void addCardTestUpdateHand(){

		Player harryPotter = new Player(card);

		characters.add("Harry Potter");
		weapons.add("deathstick");
		rooms.add("Chamber of Secrets");
        
		harryPotter.initialiseHand(hand);
        
		Checklist checkList = new Checklist(rooms,characters,weapons);
		harryPotter.addChecklist(checkList);
		harryPotter.addCard(card);

	    harryPotter.checkhand(card);
	}

	



}
