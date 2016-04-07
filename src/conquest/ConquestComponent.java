package conquest;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.EnumMap;

public class ConquestComponent extends JComponent implements BoardListener
{
    private Board board;
    private AbstractMap<SquareType, Color> colorMap;

    static final int SQUARE_SIZE= 25;

    public ConquestComponent(Board board) {
	this.board = board;
	this.board.addBoardListener(this);

	colorMap = new EnumMap<SquareType, Color>(SquareType.class);
	colorMap.put(SquareType.EMPTY, Color.GRAY);
	colorMap.put(SquareType.SCOUT, Color.CYAN);
	colorMap.put(SquareType.SOLDIER, Color.BLUE);
	colorMap.put(SquareType.KNIGHT, Color.ORANGE);
	colorMap.put(SquareType.CHAMPION, Color.YELLOW);
    }

    @Override public Dimension getPreferredSize() {
        super.getPreferredSize();
        return new Dimension(this.board.getWidth() * SQUARE_SIZE, this.board.getHeight() * SQUARE_SIZE);
    }

    public void boardChanged() {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int i = 0; i < this.board.getWidth(); i++) {
            for (int j = 0; j < this.board.getHeight(); j++) {
                g2d.setColor(colorMap.get(this.board.getSquareType(i, j)));
                g2d.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
}
