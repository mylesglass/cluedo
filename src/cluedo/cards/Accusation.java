package cluedo.cards;

public class Accusation {

	private String killer;
    private String crimeScene;
	private String weapon;

	/**
	*contains three cards (one of each type) for making accusations
	*and suggestions as to the nature of the crime.
	*@author Neal Hartley && Myles Glass
	*/

	public Accusation(String c, String r, String w){

		killer= c;
		crimeScene = r;
		weapon = w;

	}


	public String getKiller(){return killer;}
	public String getScene(){return crimeScene;}
	public String getWeapon(){return weapon;}


	/**
	 * equals method for comparing an object with an accusation.
	 * @param Object o
	 * @return Boolean
	 */
	public boolean equals(Object o){
		if(!(o instanceof Accusation)){return false;}
	    Accusation win = (Accusation) o;
	    if(win.getKiller().equals(this.getKiller()) &&  win.getScene().equals(this.getScene()) && win.getWeapon().equals(this.getWeapon())){
	    	return true;
	    }

		return false;
	}


}
