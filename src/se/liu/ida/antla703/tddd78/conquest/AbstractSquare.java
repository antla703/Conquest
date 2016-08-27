package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Abstract class for all squares
 */
public abstract class AbstractSquare implements Square {

    private PlayerType player;
    private int movement;
    private int hitpoints;
    private int damage;
    private int range;
    private Color color;

    protected AbstractSquare(PlayerType player, int hitpoints, int damage, int movement, int range, Color color1, Color color2){
	this.player = player;
	this.hitpoints = hitpoints;
	this.damage = damage;
	this.movement = movement;
	this.range = range;
	if (player == PlayerType.PLAYER1){
	    this.color = color1;
	}
	else{
	    this.color = color2;
	}
    }

    @Override public PlayerType getPlayer(){
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

    @Override public int getRange()
            {
        	return this.range;
            }

    @Override public Color getColor() { return this.color; }

    @Override public void takeDamage(int damage){

	this.hitpoints -= damage;

    }

    @Override public void battlecry(){
	this.hitpoints += 1;
	this.damage += 1;
    }

    @Override public void draw(final Graphics g, int x, int y) {

    	g.setColor(this.color);
	int size = ConquestComponent.getSquareSize();
    	g.fillRect(x * size, y * size, size, size);
	g.setColor(Color.black);
	g.drawRect(x * size, y * size, size, size);

	int damageFontSize = ConquestComponent.getSquareSize()/5;
	int hitpointsFontSize = damageFontSize * 2;

	g.setFont(new Font("TimesRoman", Font.PLAIN, damageFontSize));
	g.drawString(String.valueOf(this.damage), x * size + size/6, y * size + 5 * size/6);
	g.setFont(new Font("TimesRoman", Font.PLAIN, hitpointsFontSize));
	g.drawString(String.valueOf(this.hitpoints), x * size + size/2, y * size + size/2);

    }
}
