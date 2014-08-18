package cluedo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import cluedo.cards.Card;
import cluedo.cards.CharacterCard;
import cluedo.cards.RoomCard;
import cluedo.cards.WeaponCard;
/**
 * CardParser is made to load a text file with a list of all cards names, and create a deck for playing
 * cluedo out of them. Cards are divided into types with each set of type being headed by an all capital
 * description of that type (ROOMS, CHARACTERS, and WEAPONS) more names can be added to create a bigger deck
 * X terminates the parser.
 * @author Neal Hartley && Myles Glass
 *
 */
public class CardParser {


	private ArrayList<Card> cards;
	private ArrayList<String> roomNames;
	private ArrayList<String> characterNames;
	private ArrayList<String> charColor;
	private ArrayList<String> weaponNames;

	public CardParser(){
		cards = new ArrayList<Card>();
		roomNames = new ArrayList<String>();
		characterNames = new ArrayList<String>();
		charColor = new ArrayList<String>();
		weaponNames = new ArrayList<String>();

	}
	public void parseCards(File file){
		try {
			MyUtils.Log("[card parser] parseing cards!");
			Scanner scanner = new Scanner(file);
			String strLine = scanner.nextLine();

			int i = 1;
			if(strLine.equals("ROOMS")){
				strLine=scanner.nextLine();
				while(!strLine.equals("CHARACTERS")){
					MyUtils.Log("[CardParser] adding room: " + strLine);
					RoomCard rc = new RoomCard(strLine);
					rc.addImage("src/images/cards/room-"+i+".png");
					i++;
					cards.add(rc);
					roomNames.add(strLine);
					strLine=scanner.nextLine();
				}
			}

			i = 1;
			if(strLine.equals("CHARACTERS")){
				strLine=scanner.nextLine();
				while(!strLine.equals("WEAPONS")){
					MyUtils.Log("[CardParser] adding character: " + strLine);
					CharacterCard cc = new CharacterCard(strLine);
					cc.addImage("src/images/cards/char-"+i+".png");
					cc.addPlayerIcon("src/images/charicon/playericon-"+i+".png");
					i++;
					cards.add(cc);
					characterNames.add(strLine);
					strLine=scanner.nextLine();
					if(strLine.charAt(0) == '#') {
						charColor.add(strLine);
						strLine = scanner.nextLine();
					}

				}

			}

			i = 1;
			if(strLine.equals("WEAPONS")){
				strLine=scanner.nextLine();
				while(!strLine.equals("X")){
					MyUtils.Log("[CardParser] adding weapon: " + strLine);
					WeaponCard wc = new WeaponCard(strLine);
					wc.addImage("src/images/cards/weapon-"+i+".png");
					wc.addIcon("src/images/icons/icon-"+i+".png");
					i++;
					cards.add(wc);
					weaponNames.add(strLine);
					strLine=scanner.nextLine();
				}

			}

			MyUtils.Log("[CardParser] Finished! deck Created.");
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Card> getDeck(){
		return this.cards;
	}

	/**
	 * Get a list of all room names
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getRoomNames() {
		return this.roomNames;
	}

	/**
	 * Get a list of all character names
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getCharacterNames() {
		return this.characterNames;
	}

	/**
	 * Get a list of all character colors
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getCharacterColors() {
		return this.charColor;
	}

	/**
	 * Get a list of all weapon names
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getWeaponNames() {
		return this.weaponNames;
	}

}
