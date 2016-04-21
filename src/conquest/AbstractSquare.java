package conquest;
public abstract class AbstractSquare implements Square {

    protected int player;

    protected AbstractSquare(int player, int hitpoints, int damage, int movement){

	if (player > 2 || player < 0){

	    throw new IllegalArgumentException("Invalid player.");
	}

	this.player = player;

    }

    public int getPlayer(){
	return player;
    }

}
