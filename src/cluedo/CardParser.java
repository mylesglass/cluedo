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


	private ArrayList<Card> cards = new ArrayList<Card>();


	public CardParser(){



	}
	public void parseCards(File file){
	try {
		MyUtils.Log("[card parser] parseing cards!");
		Scanner scanner = new Scanner(file);
        String strLine = scanner.nextLine();



			if(strLine.equals("ROOMS")){
				strLine=scanner.nextLine();
				while(!strLine.equals("CHARACTERS")){
					MyUtils.Log("[CardParser] adding room: " + strLine);
					cards.add(new RoomCard(strLine));
					strLine=scanner.nextLine();
				}
			}
			if(strLine.equals("CHARACTERS")){
				strLine=scanner.nextLine();
				while(!strLine.equals("WEAPONS")){
					MyUtils.Log("[CardParser] adding character: " + strLine);
					cards.add(new CharacterCard(strLine));
					strLine=scanner.nextLine();
				}

			}
			if(strLine.equals("WEAPONS")){
				strLine=scanner.nextLine();
				while(!strLine.equals("X")){
					MyUtils.Log("[CardParser] adding weapon: " + strLine);
					cards.add(new WeaponCard(strLine));
					strLine=scanner.nextLine();
				}

			}







		MyUtils.Log("[CardParser] Finished! deck Created.");
		scanner.close();


	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}





}

public ArrayList getDeck(){
	return this.cards;

}


}
