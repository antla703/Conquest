package conquest;

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

    public String getDescription() {
	return "Default";
    }
}
