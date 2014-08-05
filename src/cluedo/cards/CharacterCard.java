package cluedo.cards;

/**
 * Represents a character card in the game cluedo.
 * @author myles
 *
 */
public class CharacterCard implements Card {
	private String name;
	
	public CharacterCard(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of this character card
	 * @return name
	 */
	public String getName() {
		return name;
	}

}
