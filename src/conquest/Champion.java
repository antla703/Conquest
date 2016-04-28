package conquest;

import java.awt.*;

/**
 * Champion type square
 */
public class Champion extends AbstractSquare {

    private Color color;

    public Champion(Player player){

	super(player, 5, 3, 2);

	if (player != Player.PLAYER1 && player != Player.PLAYER2){

	    throw new IllegalArgumentException("Invalid player");
	}

	this.setColor();
    }

    private void setColor(){
	Player player = this.getPlayer();

	if (player == Player.PLAYER1){
	    this.color = Color.YELLOW;
	}

	else{
	    this.color = Color.ORANGE;
	}
    }

    @Override public Color getColor() {
	return this.color;
    }
}