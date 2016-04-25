package conquest;
public abstract class AbstractSquare implements Square {

    protected int player;
    protected int movement;
    private int hitpoints;
    private int damage;

    protected AbstractSquare(int player, int hitpoints, int damage, int movement){

	if (player > 2 || player < 0){

	    throw new IllegalArgumentException("Invalid player.");
	}

	this.player = player;

    }

    @Override public int getPlayer(){
	return player;
    }

    public int getMovement(){
    	return this.movement;
        }

    public int getHitpoints(){
    	return this.hitpoints;
        }

    public int getDamage()
        {
    	return this.damage;
        }

    public void takeDamage(int damage){

	this.hitpoints -= damage;

    }

}
