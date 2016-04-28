package conquest;

import java.awt.*;

/**
 * Square interface. Represents units on the board, empty and outside squares.
 */
public interface Square {

    Player getPlayer();

    int getMovement();

    int getHitpoints();

    int getDamage();

    void takeDamage(int damage);

    void battlecry();

    Color getColor();

    void draw(final Graphics g, int x, int y);

}
