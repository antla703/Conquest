package conquest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents a board
 */
public class Board
{
    private SquareType[][] squares;
    private List<BoardListener> listenerList;
    private boolean gameOver = false;
    //private CollisionHandler collisionHandler = new DefaultCollisionHandler();
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private int curretPlayer = 1;

    static final int DEFAULT_WIDTH = 9;
    static final int DEFAULT_HEIGHT = 9;

    public Board(int mode) {
        switch(mode){
            case 1:
                break;
            default:
                this.width = DEFAULT_WIDTH;
                this.height = DEFAULT_HEIGHT;
                break;
        }
        this.squares = new SquareType[width][height];
        listenerList = new ArrayList<BoardListener>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                setSquareType(i, j, SquareType.EMPTY);
            }
        }
        spawnUnits(mode);
	this.notifyListeners();
    }

    private void spawnUnits(int mode){
        switch(mode){
            case 1:
                break;
            default:
                setSquareType(0, 0, SquareType.SCOUT_1);
                setSquareType(2, 0, SquareType.KNIGHT_1);
                setSquareType(4, 0, SquareType.CHAMPION_1);
                setSquareType(6, 0, SquareType.KNIGHT_1);
                setSquareType(8, 0, SquareType.SCOUT_1);
                setSquareType(1, 1, SquareType.SOLDIER_1);
                setSquareType(3, 1, SquareType.SCOUT_1);
                setSquareType(5, 1, SquareType.SCOUT_1);
                setSquareType(7, 1, SquareType.SOLDIER_1);

                setSquareType(0, 8, SquareType.SCOUT_2);
                setSquareType(2, 8, SquareType.KNIGHT_2);
                setSquareType(4, 8, SquareType.CHAMPION_2);
                setSquareType(6, 8, SquareType.KNIGHT_2);
                setSquareType(8, 8, SquareType.SCOUT_2);
                setSquareType(1, 7, SquareType.SOLDIER_2);
                setSquareType(3, 7, SquareType.SCOUT_2);
                setSquareType(5, 7, SquareType.SCOUT_2);
                setSquareType(7, 7, SquareType.SOLDIER_2);
        }
    }

    public int getWidth() {
        return this.squares.length;
    }

    public int getHeight() {
        return this.squares[0].length;
    }

    protected void setSquareType(int x, int y, SquareType st) {
        this.squares[x][y] = st;
        this.notifyListeners();
    }

    public SquareType getSquareType(int x, int y) {
        return this.squares[x][y];
    }

    public int getPlayer(int x, int y) {
        return 1;
    }

    /**public void resetCollisionHandler(){
        this.collisionHandler = new DefaultCollisionHandler();
    }**/

    public void addBoardListener(BoardListener bl) {
        this.listenerList.add(bl);
    }

    private void notifyListeners() {
        for (BoardListener boardListener : listenerList) {
            boardListener.boardChanged();
        }
    }

    private final Action doOneStep = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    };

    //private final Timer clockTimer = new Timer(TICK_DELAY, doOneStep);

    private void tick() {
	if (this.gameOver) {
	    this.clearBoard();
	    String message = " Continue? ";
	    String title = " Tetris ";
	    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
	    if (reply == JOptionPane.NO_OPTION) { System.exit(0); }
	    this.gameOver = false;
	}

	this.notifyListeners();
    }

    private void deleteRow(int row) {
        for (int i = row; i > 0; i--) {
            for (int column = 0; column < this.getWidth(); column++) {
                this.setSquareType(column, i, this.getSquareType(column, i - 1));
            }
        }

        for (int column = 0; column < this.getWidth(); column++) {
            this.setSquareType(column, 0, SquareType.EMPTY);
        }
    }

    protected boolean isSquareEmpty(int x, int y) {
        if (this.getSquareType(x, y) == SquareType.EMPTY) {
            return true;
        }
        return false;
    }

    private void clearBoard() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
		squares[i][j] = SquareType.EMPTY;
            }
        }
        this.notifyListeners();
    }

    private void resetBoard() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
		switch(i){
                    case 0:

                }
            }
        }
    }

    public void updateBoard() {
        //clockTimer.setCoalesce(true);
        //clockTimer.start();
    }
}