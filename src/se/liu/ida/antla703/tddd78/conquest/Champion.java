package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Champion type square
 */
public class Champion extends AbstractSquare {

    public Champion(PlayerType player){

	super(player, 5, 3, 2, 1, Color.YELLOW, Color.ORANGE);

	if (player != PlayerType.PLAYER1 && player != PlayerType.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}
    }
}