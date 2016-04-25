package conquest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConquestComponent extends JComponent implements BoardListener {

    private Board board;

    static final int SQUARE_SIZE= 50;

    public ConquestComponent(Board board) {
	this.board = board;
	this.board.addBoardListener(this);
	addMouseListener(new MouseAdapter(){
	    public void mousePressed(MouseEvent me){
		Point point = me.getPoint();
		selectPoint(new Point((int)point.getX() / 50, (int)point.getY() / 50));
		System.out.println((int)point.getX()/50);
	    }
	});
    }

    public static int getSquareSize(){
	return SQUARE_SIZE;
    }

    @Override public Dimension getPreferredSize() {
        super.getPreferredSize();
        return new Dimension(this.board.getWidth() * SQUARE_SIZE, this.board.getHeight() * SQUARE_SIZE);
    }

    public void selectPoint(Point point){

	this.board.setActive(point);

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
		Square square = board.getSquare(i, j);
		square.draw(g2d, i, j);
            }
        }
    }
}
