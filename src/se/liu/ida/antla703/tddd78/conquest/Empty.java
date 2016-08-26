package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Empty type square
 */
public class Empty extends AbstractSquare {

    public Empty(){
	super(PlayerType.NONE, 1, 0, 0, 0, Color.lightGray, Color.lightGray);
    }

    @Override public void draw(final Graphics g, int x, int y) {

    	g.setColor(this.getColor());
	int size = ConquestComponent.getSquareSize();
    	g.fillRect(x * size, y * size, size, size);
	g.setColor(Color.black);
	g.drawRect(x * size, y * size, size, size);
    }
}