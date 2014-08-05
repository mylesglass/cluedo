package cluedo.cards;


/**
 * Represents weapon cards.
 * @author myles
 *
 */
public class WeaponCard implements Card {
	private String name;
	
	public WeaponCard(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of this weapon
	 * @return name
	 */
	public String getName() {
		return name;
	}

}
