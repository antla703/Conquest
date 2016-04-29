package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Soldier type square
 */
public class Soldier extends AbstractSquare {

    private Color color;

    public Soldier(Player player){

	super(player, 3, 1, 2);

	if (player != Player.PLAYER1 && player != Player.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}

	this.setColor();
    }

    private void setColor(){
	Player player = this.getPlayer();

	if (player == Player.PLAYER1){
	    this.color = Color.GRAY;
	}

	else{
	    this.color = Color.DARK_GRAY;
	}
    }

    @Override public Color getColor() {
	return this.color;
    }
}