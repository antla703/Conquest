package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Knight type square
 */
public class Knight extends AbstractSquare {

    private Color color;

    public Knight(PlayerType player){

	super(player, 2, 3, 2, 1, Color.PINK, Color.RED);

	if (player != PlayerType.PLAYER1 && player != PlayerType.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}
    }
}
