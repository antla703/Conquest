package conquest;

import java.awt.*;

public interface Square {

    int getPlayer();

    public int getMovement();

    public int getHitpoints();

    public int getDamage();

    public void takeDamage(int damage);

    public void draw(final Graphics g, int x, int y);

}
