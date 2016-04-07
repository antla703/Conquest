package conquest;

public final class BoardTest {
    public static void main(String[] args) {
        Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        ConquestFrame frame = new ConquestFrame(board);
        frame.pack();
        frame.setVisible(true);
	board.updateBoard();
    }
    static final int BOARD_WIDTH = 8;
    static final int BOARD_HEIGHT = 15;
}