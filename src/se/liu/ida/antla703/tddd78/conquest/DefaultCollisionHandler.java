package se.liu.ida.antla703.tddd78.conquest;

/**
 * Default collision handler.
 * Whenever active square hits a another non-empty square, a collision is detected.
 * Offset values used to define in which direction to detect collisions.
 */
public class DefaultCollisionHandler implements CollisionHandler
{
    public boolean hasCollision(int xOffset, int yOffset, Board board) {
	if (board.isEmpty(board.getActivePos().x + xOffset,
			   board.getActivePos().y + yOffset)){
	    return false;
	}
        return true;
    }

    public boolean hasEnemy(int xOffset, int yOffset, Board board) {
	if (board.isEnemy(board.getActivePos().x + xOffset,
			  board.getActivePos().y + yOffset)){
	    return true;
	}
        return false;
    }
}
