package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Knight type square
 */
public class Knight extends AbstractSquare {

    private Color color;

    public Knight(Player player){

	super(player, 2, 3, 2);

	if (player != Player.PLAYER1 && player != Player.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}

	this.setColor();
    }

    private void setColor(){
	Player player = this.getPlayer();

	if (player == Player.PLAYER1){
	    this.color = Color.PINK;
	}

	else{
	    this.color = Color.RED;
	}
    }

    @Override public Color getColor() {
	return this.color;
    }
}
