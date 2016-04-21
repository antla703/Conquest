package conquest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a board
 */
public class Board
{
    private Square[][] squares;
    private List<BoardListener> listenerList;
    private boolean gameOver = false;
    //private CollisionHandler collisionHandler = new DefaultCollisionHandler();
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private int currentPlayer = 1;

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
        this.squares = new Square[width][height];
        listenerList = new ArrayList<BoardListener>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                setSquare(i, j, new EmptySquare(0));
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
                setSquare(0, 0, new Scout(1));
		setSquare(2, 0, new Scout(1));
		setSquare(4, 0, new Scout(1));
		setSquare(6, 0, new Scout(1));
		setSquare(8, 0, new Scout(1));
		setSquare(1, 1, new Scout(1));
		setSquare(3, 1, new Scout(1));
		setSquare(5, 1, new Scout(1));
		setSquare(7, 1, new Scout(1));

		setSquare(0, 8, new Scout(2));
		setSquare(2, 8, new Scout(2));
		setSquare(4, 8, new Scout(2));
		setSquare(6, 8, new Scout(2));
		setSquare(8, 8, new Scout(2));
		setSquare(1, 7, new Scout(2));
		setSquare(3, 7, new Scout(2));
		setSquare(5, 7, new Scout(2));
		setSquare(7, 7, new Scout(2));
        }
    }

    public int getWidth() {
        return this.squares.length;
    }

    public int getHeight() {
        return this.squares[0].length;
    }

    public int getCurrentPlayer(){

	return this.currentPlayer;

    }

    protected void setSquare(int x, int y, Square s) {
        this.squares[x][y] = s;
        this.notifyListeners();
    }

    public Square getSquare(int x, int y) {

	return this.squares[x][y];

    }

    public int getPlayer(int x, int y) {

	Square square = getSquare(x,y);

	if(square.getPlayer() == 1){
	    return 1;
	}
	else if(square.getPlayer() == 2){
	    return 2;
	}
	else{
	    return 0;
	}

    }

    public boolean isPlayer(int x, int y, int player){

	if(getPlayer(x, y) == player){
	    return true;
	}
	else{
	    return false;
	}

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
                this.setSquare(column, i, this.getSquare(column, i - 1));
            }
        }

        /**for (int column = 0; column < this.getWidth(); column++) {
            this.setSquare(column, 0, new EmptySquare(0));
        }**/
    }

    protected boolean isSquareEmpty(int x, int y) {
        if (this.getSquare(x, y) == SquareType.EMPTY) {
            return true;
        }
        return false;
    }

    private void clearBoard() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
		squares[i][j] = new EmptySquare(0);
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