package cluedo;

import java.util.ArrayList;
import java.util.List;

import cluedo.cards.Card;
import cluedo.cards.CharacterCard;

public class Player {


	private String name;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private Checklist checklist = new Checklist();
    private Position position;
     /**
      * creates a player with all the necessary equipment for playing
      * the game.
      * @param name
      * @author Neal Hartley && Myles Glass
      */
	public Player(CharacterCard card){
		this.name = card.getName();


	}

	/**
	 * returns player name.
	 * @return name.
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * adds a single card to players hand
	 * and checks off the card on the players list.
	 * @param card
	 */
	public void addCard(Card card){
		hand.add(card);
		checklist.checkOff(card);
	}

	/**
	 * adds a list of cards to a players hand,
	 * also checks each card off from their checklist.
	 * @param cards
	 */
	public void addCards(List<Card> cards){
		for(Card c: cards){
			hand.add(c);
			checklist.checkOff(c);
		}
	}

	/**
	 * checks hand to see if it contains a card.
	 * @param card
	 * @return Boolean
	 */
	public Boolean checkhand(Card card){
		if(hand.contains(card)){
			return true;
		}
		return false;

	}


	/**
	 * sets position of player.
	 * @param pos
	 */
	public void setPos(Position pos){
		this.position = pos;
	}
	/**
	 * returns position of player.
	 * @return position
	 */
	public Position getPos(){
		return this.position;
	}


}
