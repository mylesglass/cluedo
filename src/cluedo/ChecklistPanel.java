package cluedo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class ChecklistPanel extends JPanel {
	private int width;
	private int height;

	private final int OFFSET = 30;
	private final int SPACING = 15;

	private ArrayList<String> rooms;
	private ArrayList<String> characters;
	private ArrayList<String> weapons;

	private Checklist currentChecklist;

	private boolean hasInit = false;
	private boolean checklistAdded;

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
		checklistAdded = false;
	}

	public void setCurrentChecklist(Checklist c) {
		this.currentChecklist = c;
		this.checklistAdded = true;
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
			g.setColor(Color.BLACK);
			if(currentChecklist.hasCardChecked(room)) {
				g.setColor(Color.LIGHT_GRAY);
			}
			g.drawString("    "+room, OFFSET, OFFSET + (SPACING * lineCount));
			lineCount++;
		}

		// Draw each of the character names
		lineCount++;
		g.setColor(Color.BLACK);
		g.drawString("CHARACTERS", OFFSET, OFFSET + (SPACING * lineCount));
		lineCount++;
		for(String character : characters) {
			g.setColor(Color.BLACK);
			if(currentChecklist.hasCardChecked(character)) {
				g.setColor(Color.LIGHT_GRAY);
			}
			g.drawString("    "+character, OFFSET, OFFSET + (SPACING * lineCount));
			lineCount++;
		}

		// Draw each of the weapon names
		lineCount++;
		g.setColor(Color.BLACK);
		g.drawString("WEAPONS", OFFSET, OFFSET + (SPACING * lineCount));
		lineCount++;
		for(String weapon : weapons) {
			g.setColor(Color.BLACK);
			if(currentChecklist.hasCardChecked(weapon)) {
				g.setColor(Color.LIGHT_GRAY);
			}
			g.drawString("    "+weapon, OFFSET, OFFSET + (SPACING * lineCount));
			lineCount++;
		}
	}
}
