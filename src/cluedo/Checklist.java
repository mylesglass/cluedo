package cluedo;

import java.util.ArrayList;
import java.util.HashMap;

import cluedo.cards.Card;

public class Checklist {
  private HashMap<String, Boolean> checklist;

	public Checklist(ArrayList<String> room, ArrayList<String> character, ArrayList<String> weapon){
		this.checklist = new HashMap<String, Boolean>();

		//all character cards: false.
		for(String roomName : room) checklist.put(roomName, false);

		//all weapon cards: false.
		for(String charName : character) checklist.put(charName, false);

		//all Room cards: false.
		for(String weaponName : weapon) checklist.put(weaponName, false);

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

	public void printChecklist() {
		for(String str : checklist.keySet()) {
			MyUtils.Log("[Checklist] "+str+", "+checklist.get(str));
		}
	}
	
	public HashMap checkList(){
		
		return checklist;
		
	}
}
