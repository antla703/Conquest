package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Halberdier type square
 */
public class Halberdier extends AbstractSquare {

    /** R value for a darker green*/
    public static final int RVAL = 11;

    /** G value for a darker green*/
    public static final int GVAL = 156;

    /** B value for a darker green*/
    public static final int BVAL = 49;


    public Halberdier(PlayerType player){
	super(player, 1, 2, 3, 2, Color.GREEN, new Color(RVAL, GVAL, BVAL));

	if (player != PlayerType.PLAYER1 && player != PlayerType.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}
    }
}