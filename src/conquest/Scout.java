package conquest;

import java.awt.*;

public class Scout extends AbstractSquare {

    private int movement = 3;
    private int hitpoints = 1;
    private int damage = 1;
    private Color color;

    public Scout(int player){

	super(player, 1, 1, 3);

	if (player == 0){

	    throw new IllegalArgumentException("Invalid player: 0 (neutral)");
	}

	this.color = this.getColor();
    }

    private Color getColor(){
	int player = this.getPlayer();

	if (player == 1){
	    return Color.CYAN;
	}
	else{
	    return Color.BLUE;
	}
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

    @Override public void draw(final Graphics g) {

    	g.setColor(color);
	int size = ConquestComponent.getSquareSize();
    	g.drawRect(x*size, y*size, size, size);

        }

    public void takeDamage(int damage){

	this.hitpoints -= damage;

    }

}
