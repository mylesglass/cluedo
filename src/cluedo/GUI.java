package cluedo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sound.SFX;
import sound.SoundTrack;
import cluedo.cards.Accusation;
import cluedo.cards.Card;
import cluedo.cards.WeaponCard;
import cluedo.squares.DoorSquare;
import cluedo.squares.HallSquare;
import cluedo.squares.RoomSquare;
import cluedo.squares.Square;
import cluedo.squares.TunnelSquare;

/**
 * Responsible for displaying any information that the user needs to play the game.
 * @author myles & neal
 *	TODO allow drawing the deck to player panel
 *	TODO draw dice roll to player panel
 *	TODO accusation button on player panel
 */
public class GUI {
	private final int SQUARE_SIZE = 20;
	private final int PANEL_SIZE = 300;
	private final int MENU_BAR_SIZE = 60;

	private int gameWidth;
	private int gameHeight;

	private int boardWidth;
	private int boardHeight;

	private int checkPanelWidth;
	private int checkPanelHeight;

	private int playerPanelWidth;
	private int playerPanelHeight;

	//the grand pubah, the one and holy, the winning accusation!
	private Accusation winner;

	// Checklist information
	public ArrayList<String> roomNames;
	public ArrayList<String> characterNames;
	public ArrayList<String> weaponNames;
	public ArrayList<Card> cards;
	private ArrayList<WeaponCard> weapons;


	//number of players, storing here temporarily. && Characters in order of player.
	private int numPlayers;
	private ArrayList<String> charactersInPlay;

	//List of each of the users names
	private ArrayList<String> usernames;

	// The player whose turn it is currently
	//and players in play
	private Player currentPlayer;
	private ArrayList<Player> players;

    //Sound components
	SoundTrack music;

	// GUI Components
	private BoardPanel boardPanel;
	private ChecklistPanel checkListPanel;
	private PlayerPanel playerPanel;
	private JFrame container;
	JButton newGameButton;

	private Board board;
	private String state = "INIT";
	private Boolean ready = false;
	private Boolean gameFinished;

	private Boolean canEnter = false;
	private Boolean inRoom = false;

	//Turn System
	private Square selectedSquare;


	/**
	 * Construct GUI Component for displaying any information needed for the user to play game
	 */
	public GUI() {

	}

	/**
	 * Take Turn.
	 * Use current player, and how many steps they have (via roll)
	 * in order to allow player to move across the board.
	 *
	 */
	public void takeTurn(int steps) {
		int stepsRemaining  = steps;
		playerPanel.setStepsRemaining(steps);
		selectedSquare = null;
		MyUtils.Log("[GUI] "+currentPlayer.getName()+"  taking turn, rolled a "+stepsRemaining);
		while(stepsRemaining > 0) {
			MyUtils.Pause(100); // FIXME something more elegant here please

			//if a player moves into a doorsquare ends their turn and moves them into the room.
			if(selectedSquare != null && selectedSquare instanceof DoorSquare){
				//math for working out if they can move that far.
				int finalSum;
				int xDiff= selectedSquare.getPosition().getX()-currentPlayer.getPos().getX();
				int yDiff= selectedSquare.getPosition().getY()-currentPlayer.getPos().getY();
				xDiff = Math.abs(xDiff);
				yDiff = Math.abs(yDiff);

				finalSum = xDiff+yDiff;
				//if they have enough movement left they can make move, updates all variables and details appropriate.
				if(finalSum <= stepsRemaining) {
					stepsRemaining = stepsRemaining - finalSum;
					currentPlayer.setPos(selectedSquare.getPosition());
					drawBoard();
					currentPlayer.setSquare("D");
					stepsRemaining = 0;
					new SFX("src/sounds/door.wav");

				}


			}

			//if they try to move to a hallsquare works out what proportion of thier movement they have used in order to walk that far. or if they can move thier at all.
			if(selectedSquare != null && selectedSquare instanceof HallSquare) {
				int finalSum;



				int xDiff= selectedSquare.getPosition().getX()-currentPlayer.getPos().getX();
				int yDiff= selectedSquare.getPosition().getY()-currentPlayer.getPos().getY();
				xDiff = Math.abs(xDiff);
				yDiff = Math.abs(yDiff);

				finalSum = xDiff+yDiff;



				if(finalSum <= stepsRemaining) {
					stepsRemaining = stepsRemaining - finalSum;

					currentPlayer.setPos(selectedSquare.getPosition());
					drawBoard();
					selectedSquare = null;
					playerPanel.setStepsRemaining(stepsRemaining);
					drawPlayerPanel();

					currentPlayer.setSquare("H");
				}



			}
		}
	}


	/**
	 * Method called in game in order let a character take a turn starting from within a room.
	 */
	public void takeRoomTurn(){
		int result = JOptionPane.showConfirmDialog(container,  "Do you wish to make a suggestion?",null, JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.NO_OPTION) {
			moveOutRoom();
		}
		else{
			Object[] wN = weaponNames.toArray();
			Object[] cN = characterNames.toArray();

			Object murderWeapon = JOptionPane.showInputDialog(container, "choose the murder weapon!",
					"Player selection", JOptionPane.QUESTION_MESSAGE, null, wN,wN[0] );
			//if hits cancel, starts turn over.
			if(murderWeapon == null){takeRoomTurn(); return;}

			Object murderer = JOptionPane.showInputDialog(container, "choose the murderer!",
					"Player selection", JOptionPane.QUESTION_MESSAGE, null, cN,cN[0] );
			//if hits cancel starts turn over.
			if(murderer == null){takeRoomTurn(); return;}



			RoomSquare square = (RoomSquare)board.getSquareAt(currentPlayer.getPos().getX(), currentPlayer.getPos().getY()) ;
			Room room = square.getRoom();

			String killer = (String)murderer;
			String weap = (String)murderWeapon;
			String scene = room.getName();

			for(WeaponCard wc : weapons) {
				if(wc.getName().equals(weap)) {
					wc.setRoom(room);
					wc.setPosition(room.getRandPos());
					drawGame();
				}
			}

			boardPanel.updateWeapons(weapons);
			MyUtils.Log("[GUI] room accusing from: "+scene);

			for(Player p: players){
				if(p.getName().equals(killer)){

					p.setPos(room.getRandPos());
					p.setSquare("R");

					this.drawGame();

				}
			}

			Accusation suggestion = new Accusation(killer, scene, weap);
			Card clue = checkAccusation(suggestion);

			if(clue!=null){

				currentPlayer.getChecklist().checkOff(clue) ;
				JOptionPane.showMessageDialog(container,"you have been shown evidence disputing "+clue.getName()+ ", and have checked it off. ");
			}

			else{
				int finale = JOptionPane.showConfirmDialog(container,  "Do you wish to make this accusation!?",null, JOptionPane.YES_NO_OPTION);
				if(finale == JOptionPane.YES_OPTION) {


					if(suggestion.equals(winner)){

						music.stopMusic();
						new SFX("src/sounds/applause.wav");


						int readerBewareYouChoosetheScare = JOptionPane.showConfirmDialog(container,  "You just won man!!!! Collect your prize?",null, JOptionPane.YES_NO_OPTION);

						if(readerBewareYouChoosetheScare == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(container,  "Your prize is the honour of getting to give us an A+ ;)");
							System.exit(0);
						}



					}
					else{
						new SFX("src/sounds/peoplelaugh.wav");
						JOptionPane.showMessageDialog(container,  "You risked it for the biscuit and unfortuantely it didn't pay off, now you have to give us an A+.");
						currentPlayer.setSquare("$");
						JOptionPane.showMessageDialog(container,  "remaining players may keep playing.");


					}
				}
			}
		}
	}

	public void moveOutRoom(){
		selectedSquare = null;
		Boolean loopClause = true;
		JOptionPane.showMessageDialog(container,  "select a door square in current room to leave from, (whisper* or maybe a secret tunnel).");
		while(loopClause) {
			MyUtils.Pause(100);

			Position current = currentPlayer.getPos();
			RoomSquare square = (RoomSquare)board.getSquareAt(current.getX(), current.getY());

			if(selectedSquare instanceof DoorSquare){
				Room thisRoom = square.getRoom();
				DoorSquare selected = (DoorSquare) selectedSquare;

				if( selected.getRoom()==thisRoom){

					currentPlayer.setPos(selectedSquare.getPosition());
					currentPlayer.setSquare("H");
					loopClause = false;
					this.drawGame();

				}

			}

			else if(selectedSquare instanceof TunnelSquare) {
				TunnelSquare selected = (TunnelSquare) selectedSquare;

				if(selected.getRoom() == square.getRoom()) {
					currentPlayer.setPos(selected.getPair().getRoom().getRandPos());
					loopClause = false;
					this.drawGame();
				}
			}

		}

	}


	public Card checkAccusation(Accusation a){
		for(Player p: players){
			if(p.equals(currentPlayer)){continue;}
			for(Card c: p.getHand()){
				// FIXME cancel both and get null point exception
				if(a.getKiller().equals(c.getName())   ||  a.getWeapon().equals(c.getName()) || a.getScene().equals(c.getName())   ){
					JOptionPane.showMessageDialog(container, p.getName()+ " has evidence to dispute your claim!");
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * Sets up various interface components needed to display GUI
	 * @param boardWidth
	 * @param boardHeight
	 */
	public void initialiseGameInterface(Board board) {
		this.board = board;

		// Set dimensions with regards to board size
		this.boardWidth = board.getWidth() * SQUARE_SIZE;
		this.boardHeight = board.getHeight() * SQUARE_SIZE; // plus the height of any other components

		// Set dimensions for other panels
		this.checkPanelWidth = PANEL_SIZE;
		this.checkPanelHeight = this.boardHeight;

		this.playerPanelWidth = this.boardWidth + checkPanelWidth;
		this.playerPanelHeight = PANEL_SIZE;

		this.gameWidth = playerPanelWidth;
		this.gameHeight = playerPanelHeight + this.boardHeight + MENU_BAR_SIZE;

		// Set Frame
		container = new JFrame("Guess Who");
		container.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		container.addWindowListener(new RadioListener());
		container.setSize(this.gameWidth, this.gameHeight + 25);
		container.setLayout(null);

		// Menu Bar
		JPanel menuPanel = new JPanel();
		newGameButton = new JButton("New Game");
		menuPanel.add(newGameButton);
		container.add(menuPanel);
		menuPanel.setBounds(0, 0, gameWidth, MENU_BAR_SIZE);

		// Board Drawing Component
		boardPanel = new BoardPanel(board);
		boardPanel.setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));

		boardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyUtils.Log("[GUI][BOARD] Selected Square is "+getSquareAt(e.getX() / SQUARE_SIZE, e.getY() / SQUARE_SIZE).toString());


				selectedSquare = getSquareAt(e.getX() / SQUARE_SIZE, e.getY() / SQUARE_SIZE);
				if(selectedSquare instanceof RoomSquare){
					MyUtils.Log("[GUI]Room: "+ ((RoomSquare)selectedSquare).getRoom().getName());

				}
			}
		});


		//CheckList Drawing Component
		checkListPanel = new ChecklistPanel(checkPanelWidth, checkPanelHeight);
		checkListPanel.setPreferredSize(new Dimension(this.checkPanelWidth, this.checkPanelHeight));
		checkListPanel.setBackground(Color.GRAY);

		// Player Panel Component
		playerPanel = new PlayerPanel(playerPanelWidth, playerPanelHeight);
		checkListPanel.setPreferredSize(new Dimension(this.playerPanelWidth, this.playerPanelHeight));
		ImageIcon door = new ImageIcon("cluedo/src/images/openDoor.jpeg");

		// Add Panels to Frame
		container.add(boardPanel);
		boardPanel.setBounds(0, MENU_BAR_SIZE + 0, boardWidth, boardHeight);
		container.add(checkListPanel);
		checkListPanel.setBounds(boardWidth, MENU_BAR_SIZE + 0, PANEL_SIZE, boardHeight);
		container.add(playerPanel);
		playerPanel.setBounds(0, MENU_BAR_SIZE + boardHeight, gameWidth, PANEL_SIZE);

		// Once all set up, make it visible
		container.setVisible(true);
		JOptionPane.showMessageDialog(container,  "Connect your speaker/ headphones : Dolby Atmos (tm) capable : 3D surround sound");

		usernames = new ArrayList<String>();

		//NewGame button press events
		newGameButton.addActionListener(new ActionListener(){




			public void actionPerformed(ActionEvent e) {
				// Construct empty list for storing character names
				new SFX("src/sounds/psycho.wav");
				JOptionPane.showMessageDialog(container,  "Mr Black has been murdered! this has taken place while you and your fellow vacationers have been staying  at Colonel Mustards"
						+ " isolated manor home.");
				JOptionPane.showMessageDialog(container,  "Who is the murderer?! it is up to you and your fellow vacationers to deduce this.");
				JOptionPane.showMessageDialog(container,  "It could be your closest friend....it could even be you!  Only one way to find out... ");
				JOptionPane.showMessageDialog(container,  "Let the games begin!! ");

				music = new SoundTrack();
				music.StartMusic();
				charactersInPlay = new ArrayList<String>();

				String[] names = new String[characterNames.size()];
				for(int i=0; i<names.length; i++){
					names[i]=characterNames.get(i);
				}
				String[] options = new String[] {"2","3","4","5","6"};
				int name = JOptionPane.showOptionDialog(container,"How many players?" ,"Number of players?",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null ,options,options[0]);
				numPlayers = name+2;

				MyUtils.Log("[GUI] New Game button pushed: " + numPlayers + " player selected");

				for(int i = 0; i<numPlayers; i++){

					Object nameInput = JOptionPane.showInputDialog(container, "Player "+(i + 1)+" enter your name:",
							"Player selection", JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(nameInput == null) {
						MyUtils.End("[GUI][INIT] Player did not enter a name!");
					}
					usernames.add((String) nameInput);

					Object selection = JOptionPane.showInputDialog(container, "Choose your player",
							"Player selection", JOptionPane.QUESTION_MESSAGE, null, names, names[0]);
					MyUtils.Log("[GUI] player selected: " + selection);

					if(selection == null){
						i--;


						JOptionPane.showMessageDialog(container,  "you selected a non active character, closing program.");

						System.exit(0);
						//MyUtils.End("[GUI][INIT] Player failed to pick a character.");
					}

					charactersInPlay.add((String) selection);

					for(int j= 0 ; j < names.length ; j++){

						if(selection.equals(names[j]) ){
							MyUtils.Log(names[j]);
							names[j]=null;
						}

					}

				}
				gameFinished = false;
				newGameButton.setEnabled(false);
				state = "GAME";
				ready = true;

			}

		});





	}

	/**
	 * Get a list of the players who have been chosen by the users of the game
	 */
	public ArrayList<String> getPlayers() {
		return this.charactersInPlay;
	}

	/**
	 * Set cards
	 */
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	/**
	 * Set Weapons
	 */
	public void setWeapons(ArrayList<WeaponCard> weapons) {
		this.weapons = weapons;
	}

	/**
	 * Update the references to the names of the different cards in the game
	 * @param list of room names
	 * @param list of char names
	 * @param list of weapon names
	 */
	public void updateCardNames(ArrayList<String> rooms, ArrayList<String> chars, ArrayList<String> weapons) {
		this.characterNames = chars;
		this.roomNames = rooms;
		this.weaponNames = weapons;
	}

	/**
	 * Get the current state of the gui
	 * @return state string
	 */
	public String getState() {
		return state;
	}

	public ArrayList<String> getUsernames() {
		return this.usernames;
	}

	/**
	 * Draw supplied list of players to board.
	 * @param players
	 */
	public void drawPlayersToBoard(ArrayList<Player> players) {
		this.players = players;
		boardPanel.setPlayers(players);
		boardPanel.repaint();
	}

	public void drawWeaponsToBoard(ArrayList<WeaponCard> weapons) {
		boardPanel.updateWeapons(weapons);
		drawBoard();
	}

	public void drawRoomNames(ArrayList<Room> rooms) {
		boardPanel.setRooms(rooms);
	}

	public boolean hasGameFinished() {
		return this.gameFinished;
	}

	public void setCurrentPlayer(Player p) {
		this.currentPlayer = p;
		playerPanel.setCurrentPlayer(p);
		checkListPanel.setCurrentPlayer(p);
		drawCheckList();
		drawPlayerPanel();
	}

	private Square getSquareAt(int x, int y) {
		return this.board.getSquareAt(x,y);
	}

	public void setReady(Boolean b){
		this.ready = b;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void drawGame() {
		drawBoard();
		drawCheckList();
		drawPlayerPanel();
	}

	private void drawBoard() {
		boardPanel.repaint();
	}

	private void drawCheckList() {
		checkListPanel.repaint();
	}

	private void drawPlayerPanel() {

		playerPanel.repaint();
	}

	public Boolean isReady(){
		return this.ready;
	}


	public void setWinner(Accusation a){
		this.winner = a;
		MyUtils.Log(this.winner.getKiller() + " : " + this.winner.getScene() + " : " + this.winner.getWeapon());
	}

	public void initialiseCheckPanel() {
		this.checkListPanel.initCheckPanel(this.roomNames, this.characterNames, this.weaponNames);
	}
}