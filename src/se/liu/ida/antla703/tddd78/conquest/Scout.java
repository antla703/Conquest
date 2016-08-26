package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Scout type square
 */
public class Scout extends AbstractSquare {

    public Scout(PlayerType player){

	super(player, 1, 2, 3, 1, Color.CYAN, Color.BLUE);

	if (player != PlayerType.PLAYER1 && player != PlayerType.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}
    }
}
