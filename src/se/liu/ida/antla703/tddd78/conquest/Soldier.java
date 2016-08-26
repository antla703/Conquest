package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Soldier type square
 */
public class Soldier extends AbstractSquare {

    public Soldier(PlayerType player){

	super(player, 3, 1, 2, 1, Color.GRAY, Color.DARK_GRAY);

	if (player != PlayerType.PLAYER1 && player != PlayerType.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}
    }
}