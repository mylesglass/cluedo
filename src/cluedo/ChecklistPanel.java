package cluedo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ChecklistPanel extends JPanel {
	private int width;
	private int height;

	private final int OFFSET = 30;
	private final int SPACING = 15;

	private ArrayList<String> rooms;
	private ArrayList<String> characters;
	private ArrayList<String> weapons;

	private boolean hasInit = false;

	public ChecklistPanel(int width, int height) {
		this.width = width;
		this.height = height;
		MyUtils.Log("[CheckListPanel] Check List Panel Created. Size: "+this.width+", "+this.height);
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
