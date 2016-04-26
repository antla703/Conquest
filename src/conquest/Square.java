package conquest;

import java.awt.*;

public interface Square {

    Player getPlayer();

    int getMovement();

    int getHitpoints();

    int getDamage();

    void takeDamage(int damage);

    Color getColor();

    void draw(final Graphics g, int x, int y);

}
