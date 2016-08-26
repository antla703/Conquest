package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Halberdier type square
 */
public class Halberdier extends AbstractSquare {
    static int R = 11;
    public Halberdier(PlayerType player){
	super(player, 1, 2, 3, 2, Color.GREEN, new Color(11, 156, 49));

	if (player != PlayerType.PLAYER1 && player != PlayerType.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}
    }
}