package cluedo;

import java.util.ArrayList;
import java.util.HashMap;

import cluedo.cards.Card;

public class Checklist {
  private HashMap<String, Boolean> checklist;


  /**
   * @author Neal Hartley && Myles Glass
   * @param room
   * @param character
   * @param weapon
   * constructs a checklist of all cards in the game.
   * sets all of them initially to false, or , unchecked.
   */
	public Checklist(ArrayList<String> room, ArrayList<String> character, ArrayList<String> weapon){
		this.checklist = new HashMap<String, Boolean>();

		//all character cards: false.
		for(String roomName : room) checklist.put(roomName, false);

		//all weapon cards: false.
		for(String charName : character) checklist.put(charName, false);

		//all Room cards: false.
		for(String weaponName : weapon) checklist.put(weaponName, false);

	}



	/**
	 * Given a single card, will check that card is contained in checklist and if so will check it off, map it to true.
	 * @param card
	 * @return bool
	 */
	public boolean checkOff(Card card){
		//if checklist contains key then sets mapping too true, ie checks it off.
		if(checklist.containsKey(card.getName())){
			checklist.put(card.getName(), true);
			return true;
		}
		return false;
	}

	/**
	 * prints out entire checklist.
	 */
	public void printChecklist() {
		for(String str : checklist.keySet()) {
			MyUtils.Log("[Checklist] "+str+", "+checklist.get(str));
		}
	}

	public boolean hasCardChecked(String cardName) {
		for(String name : checklist.keySet()) {
			if(name.equals((cardName))) {
				return checklist.get(name);
			}
		}
		return false;
	}


	@SuppressWarnings("rawtypes")
	public HashMap checkList(){
		return checklist;

	}
}
