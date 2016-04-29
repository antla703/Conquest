package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Outside type square
 */
public class Outside extends AbstractSquare
{
    private Color color = Color.WHITE;

    public Outside(){

	super(Player.OUTSIDE, 1, 0, 0);

    }

    public Color getColor(){
	return this.color;
    }
}
