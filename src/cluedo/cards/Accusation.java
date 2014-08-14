package cluedo.cards;

public class Accusation {

	CharacterCard killer;
	RoomCard crimeScene;
	WeaponCard weapon;

	/**
	*contains three cards (one of each type) for making accusations
	*and suggestions as to the nature of the crime.
	*@author Neal Hartley && Myles Glass
	*/

	public Accusation(CharacterCard c, RoomCard r, WeaponCard w){

		killer= c;
		crimeScene = r;
		weapon = w;

	}



}
