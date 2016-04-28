package conquest;

/**
 * Collision handler Interface
 */
public interface CollisionHandler
{
    public boolean hasCollision(int xOffset, int yOffset, Board board);

    public boolean hasEnemy(int xOffset, int yOffset, Board board);
}
