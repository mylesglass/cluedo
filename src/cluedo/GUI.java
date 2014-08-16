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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cluedo.cards.Card;
import cluedo.squares.Square;

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
	private final int TOKEN_SIZE = SQUARE_SIZE;

	private int gameWidth;
	private int gameHeight;

	private int boardWidth;
	private int boardHeight;

	private int checkPanelWidth;
	private int checkPanelHeight;

	private int playerPanelWidth;
	private int playerPanelHeight;

	// Checklist information
	public ArrayList<String> roomNames;
	public ArrayList<String> characterNames;
	public ArrayList<String> weaponNames;

	//number of players, storing here temporarily. && Characters in order of player.
	private int numPlayers;
	private ArrayList<String> charactersInPlay;

	// The player whose turn it is currently
	private Player currentPlayer;

	// GUI Components
	private BoardPanel boardPanel;
	private CheckListPanel checkListPanel;
	private PlayerPanel playerPanel;
	private JFrame container;

	private Board board;
	private String state = "INIT";
	private Boolean ready = false;


	/**
	 * Construct GUI Component for displaying any information needed for the user to play game
	 */
	public GUI() {

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
		this.boardHeight = board.getHeight() * SQUARE_SIZE; // plus the hieght of any other components

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
		JButton newGameButton = new JButton("New Game");
		menuPanel.add(newGameButton);
		container.add(menuPanel);
		menuPanel.setBounds(0, 0, gameWidth, MENU_BAR_SIZE);

		// Board Drawing Component
		boardPanel = new BoardPanel(board);
		boardPanel.setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));

		boardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyUtils.Log("[GUI][BOARD] Mouse clicked on a "+getSquareAt(e.getX() / SQUARE_SIZE, e.getY() / SQUARE_SIZE).toString());


			}
		});

		//CheckList Drawing Component
		checkListPanel = new CheckListPanel(checkPanelWidth, checkPanelHeight);
		checkListPanel.setPreferredSize(new Dimension(this.checkPanelWidth, this.checkPanelHeight));
		checkListPanel.setBackground(Color.GRAY);

		// Player Panel Component
		playerPanel = new PlayerPanel(playerPanelWidth, playerPanelHeight);
		checkListPanel.setPreferredSize(new Dimension(this.playerPanelWidth, this.playerPanelHeight));

		// Add Panels to Frame
		container.add(boardPanel);
		boardPanel.setBounds(0, MENU_BAR_SIZE + 0, boardWidth, boardHeight);
		container.add(checkListPanel);
		checkListPanel.setBounds(boardWidth, MENU_BAR_SIZE + 0, PANEL_SIZE, boardHeight);
		container.add(playerPanel);
		playerPanel.setBounds(0, MENU_BAR_SIZE + boardHeight, gameWidth, PANEL_SIZE);

		// Once all set up, make it visible
		container.setVisible(true);


		//NewGame button press events
		newGameButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// Construct empty list for storing character names
				charactersInPlay = new ArrayList<String>();

				String[] names = new String[characterNames.size()];
				for(int i=0; i<names.length; i++){
					names[i]=characterNames.get(i);
				}
				String[] options = new String[] {"2","3","4","5","6"};
				int name = JOptionPane.showOptionDialog(container,"How many players?" ,"number of players?",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null ,options,options[0]);
				numPlayers = name+2;

				MyUtils.Log("[GUI] New Game button pushed: " + numPlayers + " player selected");

				for(int i = 0; i<numPlayers; i++){

					Object selection = JOptionPane.showInputDialog(container, "choose your player",
							"Player selection", JOptionPane.QUESTION_MESSAGE, null, names, names[0]);
					MyUtils.Log("[GUI] player selected: " + selection);

					if(selection == null){
						MyUtils.End("Player failed to pick a character.");
					}

					charactersInPlay.add((String) selection);

					for(int j= 0 ; j < names.length ; j++){

						if(selection.equals(names[j]) ){
							MyUtils.Log(names[j]);
							names[j]=null;
						}

					}

				}

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

	/**
	 * Draw supplied list of players to board.
	 * @param players
	 */
	public void drawPlayersToBoard(ArrayList<Player> players) {
		boardPanel.setPlayers(players);
		boardPanel.repaint();
	}

	public void setCurrentPlayer(Player p) {
		this.currentPlayer = p;
		playerPanel.setCurrentPlayer(p);
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

	public void initialiseCheckPanel() {
		this.checkListPanel.initCheckPanel(this.roomNames, this.characterNames, this.weaponNames);
	}
}

class BoardPanel extends JPanel {
	private final int SQUARE_SIZE = 20;
	private int width;
	private int height;
	private Board board;
	private ArrayList<Player> players;
	private boolean hasPlayers = false;

	public BoardPanel(Board board) {
		this.board = board;
		this.width = board.getWidth() * SQUARE_SIZE;
		this.height = board.getHeight() * SQUARE_SIZE;
		MyUtils.Log("[BoardPanel] Board Panel Created. Size: "+this.width+", "+this.height);
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		this.hasPlayers = true;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		board.draw(g, SQUARE_SIZE, 0);
		if(hasPlayers) drawPlayers(g);
	}

	private void drawPlayers(Graphics g) {
		for(Player p : players) {
			p.draw(g, SQUARE_SIZE);
			MyUtils.Log("[BoardPanel] Drawing player "+p.getName());
		}
	}
}

class CheckListPanel extends JPanel {
	private int width;
	private int height;

	private final int OFFSET = 30;
	private final int SPACING = 15;

	private ArrayList<String> rooms;
	private ArrayList<String> characters;
	private ArrayList<String> weapons;

	private boolean hasInit = false;

	public CheckListPanel(int width, int height) {
		this.width = width;
		this.height = height;
		MyUtils.Log("[CheckListPanel] Check List Panel Created. Size: "+width+", "+height);
	}

	public void initCheckPanel(ArrayList<String> rooms, ArrayList<String> characters, ArrayList<String> weapons) {
		this.rooms = rooms;
		this.characters = characters;
		this.weapons = weapons;
		hasInit = true;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(hasInit) drawList(g);
	}

	private void drawList(Graphics g) {
		MyUtils.Log("[GUI] Drawing Check List");
		int lineCount = 1;

		g.setColor(Color.BLACK);

		// Draw each of the room names
		g.drawString("ROOMS", OFFSET, OFFSET + (SPACING * lineCount));
		lineCount++;
		for(String room : rooms) {
			g.drawString("    "+room, OFFSET, OFFSET + (SPACING * lineCount));
			lineCount++;
		}

		// Draw each of the character names
		lineCount++;
		g.drawString("CHARACTERS", OFFSET, OFFSET + (SPACING * lineCount));
		lineCount++;
		for(String character : characters) {
			g.drawString("    "+character, OFFSET, OFFSET + (SPACING * lineCount));
			lineCount++;
		}

		// Draw each of the weapon names
		lineCount++;
		g.drawString("WEAPONS", OFFSET, OFFSET + (SPACING * lineCount));
		lineCount++;
		for(String weapon : weapons) {
			g.drawString("    "+weapon, OFFSET, OFFSET + (SPACING * lineCount));
			lineCount++;
		}
	}
}

class PlayerPanel extends JPanel {
	private int width;
	private int height;
	private Player currentPlayer;
	private boolean hasPlayer = false;
	private final int CARD_X_POS = 80;
	private final int CARD_Y_POS = 50;
	private final int CARD_WIDTH = 132;
	private final int CARD_HEIGHT = 200;
	private final int CARD_SPACING = 10;


	public PlayerPanel(int width, int height) {
		this.width = width;
		this.height = height;
		MyUtils.Log("[PlayerPanel] Player Panel Created. Size: "+width+", "+height);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Player Panel", 100, 100);
		if(hasPlayer) {
			drawPlayerCards(g);
		}
	}

	private void drawPlayerCards(Graphics g) {
		ArrayList<Card> cards = currentPlayer.getHand();
		int count = 1;
		for(Card c : cards) {
			c.draw(g, (CARD_X_POS * count) - (CARD_SPACING * (count - 1)), CARD_Y_POS, CARD_WIDTH, CARD_HEIGHT);
			count++;
		}
	}

	public void setCurrentPlayer(Player p) {
		this.currentPlayer = p;
		this.hasPlayer = true;
	}



}


