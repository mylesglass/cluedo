package cluedo;

import java.util.HashMap;

import cluedo.cards.Card;
import cluedo.cards.CharacterCard;

public class Checklist {
  private HashMap<String, Boolean> checklist = new HashMap<String, Boolean>();
	
	public void checklist(){
		//all character cards: false.
		checklist.put("Miss Scarlett",false);
		checklist.put("Colonel Mustard",false);
		checklist.put("Mrs White",false);
		checklist.put("Reverend Green",false);
		checklist.put("Mrs Peacock",false);
		checklist.put("Professor Plum",false);
		
		//all weapon cards: false.
		checklist.put("Candlestick",false);
		checklist.put("Dagger",false);
		checklist.put("Lead pipe",false);
		checklist.put("Revolver",false);
		checklist.put("Rope",false);
		checklist.put("Spanner",false);
		
		//all Room cards: false.
		checklist.put("Kitchen",false);
		checklist.put("Ballroom",false);
		checklist.put("Conservatory",false);
		checklist.put("Billiard Room",false);
		checklist.put("Library",false);
		checklist.put("Study",false);
		checklist.put("Hall",false);
		checklist.put("Lounge",false);
		checklist.put("Dining Room",false);
	}
	//requires: a cluedo card.
	//ensures: cards mapping becomes true and returns true, or if card is wrong returns false. 
	public boolean checkOff(Card card){
		//if checklist contains key then sets mapping too true, ie checks it off.
		if(checklist.containsKey(card.getName())){
			checklist.put(card.getName(), true);
			
			return true;
		}
		
		return false;
	}
}
