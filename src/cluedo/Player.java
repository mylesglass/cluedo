package cluedo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.squares.Square;

public class Player {


	private String name;
    private ArrayList<Card> hand;
    private Checklist checklist;
    private Position position;
    private Color color;
    private String square = "H";


     /**
      * creates a player with all the necessary equipment for playing
      * the game.
      * @param name
      * @author Neal Hartley && Myles Glass
      */
	public Player(CharacterCard card){
		this.name = card.getName();
		hand = new ArrayList<Card>();
	}

	/**
	 * set color values of this player.
	 */
	public void setColor(Color c) {
		this.color = c;
	}

	/**
	 * set color values of this player.
	 */
	public void setColor(String str) {
		this.color = Color.decode(str);
	}

	/**
	 * Get the color of this player
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Get Current Hand
	 */
	public ArrayList<Card> getHand() {
		return this.hand;
	}

	/**
	 * Add a new CheckList to the players inventory
	 */
	public void addChecklist(Checklist cl) {
		this.checklist = cl;
	}

	/**
	 * Get checklist
	 */
	public Checklist getChecklist() {
		return this.checklist;
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

	public void initialiseHand(ArrayList<Card> card){

		this.hand = card;
	}

	public void setSquare(String s){
		this.square =s;
	}
	public String getSquare(){
		return this.square;

	}

	/**
	 * Draws the player token on it s current square, to the supplied graphics object
	 */
	public void draw(Graphics g, int size) {
		g.setColor(this.color);
		g.fillOval(position.getX() * size, position.getY() * size, size, size);
	}

}
