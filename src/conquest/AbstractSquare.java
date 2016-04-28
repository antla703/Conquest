package conquest;

import java.awt.*;

/**
 * Abstract class for all squares
 */
public abstract class AbstractSquare implements Square {

    protected Player player;
    protected int movement;
    private int hitpoints;
    private int damage;

    protected AbstractSquare(Player player, int hitpoints, int damage, int movement){
	this.player = player;
	this.hitpoints = hitpoints;
	this.damage = damage;
	this.movement = movement;
    }

    @Override public Player getPlayer(){
	return player;
    }

    @Override public int getMovement(){
    	return this.movement;
        }

    @Override public int getHitpoints(){
    	return this.hitpoints;
        }

    @Override public int getDamage()
        {
    	return this.damage;
        }

    @Override public void takeDamage(int damage){

	this.hitpoints -= damage;

    }

    @Override public void battlecry(){
	this.hitpoints += 1;
	this.damage += 1;
    }

    @Override public void draw(final Graphics g, int x, int y) {

    	g.setColor(this.getColor());
	int size = ConquestComponent.getSquareSize();
    	g.fillRect(x * size, y * size, size, size);
	g.setColor(Color.black);
	g.drawRect(x * size, y * size, size, size);
	g.drawString(String.valueOf(this.hitpoints), x * size + size/2, y * size + size/2);
    }

}
