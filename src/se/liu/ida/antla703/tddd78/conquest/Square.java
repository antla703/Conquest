package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;

/**
 * Square interface. Represents units on the board, empty and outside squares.
 */
public interface Square {

    PlayerType getPlayer();

    int getMovement();

    int getHitpoints();

    int getDamage();

    int getRange();

    void takeDamage(int damage);

    void battlecry();

    Color getColor();

    void draw(final Graphics g, int x, int y);

}
