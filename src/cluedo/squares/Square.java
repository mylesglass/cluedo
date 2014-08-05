package cluedo.squares;

import java.awt.Color;

import cluedo.Position;

/**
 * Square
 * 		-All Squares have a position. I guess?
 * @author myles
 *
 */
public interface Square {
	public Position getPosition();
	public void print();
	public Color getColor();
}
