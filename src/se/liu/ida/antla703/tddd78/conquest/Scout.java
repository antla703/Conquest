package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Scout type square
 */
public class Scout extends AbstractSquare {

    private Color color;

    public Scout(Player player){

	super(player, 1, 2, 3);

	if (player != Player.PLAYER1 && player != Player.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}

	this.setColor();
    }

    private void setColor(){
	Player player = this.getPlayer();

	if (player == Player.PLAYER1){
	    this.color = Color.CYAN;
	}

	else{
	    this.color = Color.BLUE;
	}
    }

    @Override public Color getColor() {
	return this.color;
    }
}
