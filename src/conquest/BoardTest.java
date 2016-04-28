package conquest;

/**
 * Board test. Iniates the game.
 * Test class is not used by others, therefore no 'private' constructor.
 */
public final class BoardTest {
    public static void main(String[] args) {
        Board board = new Board(0);
        ConquestFrame frame = new ConquestFrame(board);
        frame.pack();
        frame.setVisible(true);
    }
}