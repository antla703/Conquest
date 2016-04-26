package conquest;

public final class BoardTest {
    public static void main(String[] args) {
        Board board = new Board(0);
        ConquestFrame frame = new ConquestFrame(board);
        frame.pack();
        frame.setVisible(true);
	//board.updateBoard();

        //board.getPlayer(0,0);
    }
}