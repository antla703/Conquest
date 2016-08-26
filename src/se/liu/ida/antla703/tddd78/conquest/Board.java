package se.liu.ida.antla703.tddd78.conquest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a board
 */
public class Board
{
    private Square[][] squares;
    private List<BoardListener> listenerList;
    private int outsideWidth = DEFAULT_WIDTH;
    private int outsideHeight = DEFAULT_HEIGHT;
    private Player player1 = new Player(PlayerType.PLAYER1);
    private Player player2 = new Player(PlayerType.PLAYER2);
    private Player currentPlayer = player1;
    private Square active = null;
    private Point activePos = null;
    private int activeMovement;
    private boolean selectingUnit = true;
    private CollisionHandler collisionHandler = new DefaultCollisionHandler();
    private boolean battlecryUsedP1 = false;
    private boolean battlecryUsedP2 = false;
    private boolean win = false;

    /**
     * Default width for board
     */
    static final int DEFAULT_WIDTH = 9;

    /**
     * Default height for board
     */
    static final int DEFAULT_HEIGHT = 9;

    public Board() {
	this.outsideWidth = DEFAULT_WIDTH+2;
	this.outsideHeight = DEFAULT_HEIGHT+2;

        this.squares = new Square[this.outsideWidth][this.outsideHeight];
        listenerList = new ArrayList<BoardListener>();

        for (int i = 0; i < this.outsideWidth; i++) {
            for (int j = 0; j < this.outsideHeight; j++) {
		if (i == 0 || j == 0 || i >= this.outsideWidth-1 || j >= this.outsideHeight-1) {
		    squares[i][j] = new Outside();
  		}
  		else {
		    squares[i][j] = new Empty();
  		}
            }
        }
        spawnUnits();
	this.notifyListeners();
    }

    private void spawnUnits(){
	setSquare(0, 0, new Halberdier(PlayerType.PLAYER1));
	setSquare(2, 0, new Knight(PlayerType.PLAYER1));
	setSquare(4, 0, new Champion(PlayerType.PLAYER1));
	setSquare(6, 0, new Knight(PlayerType.PLAYER1));
	setSquare(8, 0, new Halberdier(PlayerType.PLAYER1));
	setSquare(1, 1, new Soldier(PlayerType.PLAYER1));
	setSquare(3, 1, new Scout(PlayerType.PLAYER1));
	setSquare(5, 1, new Scout(PlayerType.PLAYER1));
	setSquare(7, 1, new Soldier(PlayerType.PLAYER1));

	setSquare(0, 8, new Halberdier(PlayerType.PLAYER2));
	setSquare(2, 8, new Knight(PlayerType.PLAYER2));
	setSquare(4, 8, new Champion(PlayerType.PLAYER2));
	setSquare(6, 8, new Knight(PlayerType.PLAYER2));
	setSquare(8, 8, new Halberdier(PlayerType.PLAYER2));
	setSquare(1, 7, new Soldier(PlayerType.PLAYER2));
	setSquare(3, 7, new Scout(PlayerType.PLAYER2));
	setSquare(5, 7, new Scout(PlayerType.PLAYER2));
	setSquare(7, 7, new Soldier(PlayerType.PLAYER2));

	this.notifyListeners();
    }

    public int getWidth() {
        return this.squares.length-2;
    }

    public int getHeight() {
        return this.squares[0].length-2;
    }

    public int getCurrentPlayerInt(){

	if (this.currentPlayer.getPlayerType() == PlayerType.PLAYER1){
	    return 1;
	}
	else{
	    return 2;
	}

    }

    public boolean getWin(){
	return this.win;
    }

    public Point getActivePos() {
	return this.activePos;
    }

    public Square getSquare(int x, int y) {
	return this.squares[x+1][y+1];
    }

    private PlayerType getPlayer(int x, int y) {

	Square square = getSquare(x,y);

	return square.getPlayer();

    }

    public void setActive(Point point){

	if (selectingUnit){

	    int x = (int)point.getX();
	    int y = (int)point.getY();

	    if (getPlayer(x, y) == this.currentPlayer.getPlayerType()){

		this.activePos = point;
		this.active = getSquare(x, y);
		this.activeMovement = this.active.getMovement();
		this.toggleMove();
		System.out.println("Unit selected");
	    }
	    else {
		System.out.println("Invalid player unit");
	    }
	}
    }

    protected void setSquare(int x, int y, Square s) {
        this.squares[x+1][y+1] = s;
        this.notifyListeners();
    }

    private boolean isPlayer(int x, int y, PlayerType player){
	return getPlayer(x, y) == player;
    }

    protected boolean isEnemy(int x, int y){
	return !isEmpty(x, y) && !isOutside(x, y) && !isPlayer(x, y, this.currentPlayer.getPlayerType());
    }

    protected boolean isEmpty(int x, int y) {
	Square square = this.getSquare(x, y);
	return square.getColor().equals(Color.lightGray);
    }

    private boolean isOutside(int x, int y) {
	Square square = this.getSquare(x, y);
	return square.getColor().equals(Color.WHITE);
    }

    public void addBoardListener(BoardListener bl) {
        this.listenerList.add(bl);
    }

    private void notifyListeners() {
        for (BoardListener boardListener : listenerList) {
            boardListener.boardChanged();
	}
    }

    private boolean existsActive(){

	return this.active != null;

    }

    public void battlecry(){
	if (!this.currentPlayer.battlecryUsed && !this.selectingUnit){
	    this.active.battlecry();
	    this.currentPlayer.battlecryUsed = true;
	}

	this.notifyListeners();
    }

    public void sprint(){
	if (!this.currentPlayer.sprintUsed && !this.selectingUnit){
	    this.activeMovement += 1;
	    this.currentPlayer.sprintUsed = true;
	}
    }

    public void move(int xOffset, int yOffset){

	if (this.existsActive() && !this.selectingUnit && !this.collisionHandler.hasCollision(xOffset, yOffset, this)){
	    setSquare(this.activePos.x + xOffset, this.activePos.y + yOffset, active);
	    setSquare(this.activePos.x, this.activePos.y, new Empty());
	    this.activePos.x += xOffset;
	    this.activePos.y += yOffset;

	    if (this.activeMovement <= 1) {
		this.toggleMove();
	    }

	    this.activeMovement -= 1;
	    this.notifyListeners();
	}

    }

    public void toggleMove(){

	if (this.selectingUnit && this.existsActive()){
	    this.selectingUnit = false;
	}

	else if (!this.selectingUnit && this.existsActive()){
	    this.selectingUnit = true;
	    for (int i = 1; i <= active.getRange(); i++){
		if (this.collisionHandler.hasEnemy(i, 0, this)){
		    Square enemy = this.getSquare(this.activePos.x + i, this.activePos.y);
		    this.active.takeDamage(enemy.getDamage());
		    enemy.takeDamage(this.active.getDamage());
		}

		else if (this.collisionHandler.hasEnemy(0, i, this)){
		    Square enemy = this.getSquare(this.activePos.x, this.activePos.y + i);
		    this.active.takeDamage(enemy.getDamage());
		    enemy.takeDamage(this.active.getDamage());
		}

		else if (this.collisionHandler.hasEnemy(-i, 0, this)){
		    Square enemy = this.getSquare(this.activePos.x - i, this.activePos.y);
		    this.active.takeDamage(enemy.getDamage());
		    enemy.takeDamage(this.active.getDamage());
		}

		else if (this.collisionHandler.hasEnemy(0, -i, this)){
		    Square enemy = this.getSquare(this.activePos.x, this.activePos.y - i);
		    this.active.takeDamage(enemy.getDamage());
		    enemy.takeDamage(this.active.getDamage());
		}
	    }


	    this.clearDead();

	    if (this.currentPlayer.getPlayerType() == PlayerType.PLAYER1){
		this.checkWin(PlayerType.PLAYER2);
		this.currentPlayer = player2;
	    }

	    else {
		this.checkWin(PlayerType.PLAYER1);
		this.currentPlayer = player1;
	    }
	}

    }

    private void checkWin(PlayerType player){
	this.win = true;
	for (int i = 0; i < this.getWidth(); i++) {
	    for (int j = 0; j < this.getHeight(); j++) {
		if (isPlayer(i, j, player)){
		    this.win = false;
		}
	    }
	}
	this.notifyListeners();
    }

    private void clearDead() {
	for (int i = 0; i < this.getWidth(); i++) {
	    for (int j = 0; j < this.getHeight(); j++) {
		Square square = this.getSquare(i, j);
		if (square.getHitpoints() < 1){
		    setSquare(i, j, new Empty());
		}
	    }
	}
	this.notifyListeners();
    }

    private void clearBoard() {
	for (int i = 0; i < outsideWidth; i++) {
	    for (int j = 0; j < outsideHeight; j++) {
		if (i == 0 || j == 0 || i == outsideWidth-1 || j == outsideHeight-1) {
		    squares[i][j] = new Outside();
         	}
         	else {
		    squares[i][j] = new Empty();
		}
	    }
	}
	this.notifyListeners();
    }

    public void resetBoard() {
	this.clearBoard();
	this.spawnUnits();
	this.selectingUnit = true;
	this.currentPlayer = player1;
	this.active = null;
	this.activePos = null;
	this.activeMovement = 0;
	this.player1.battlecryUsed = false;
	this.player2.battlecryUsed = false;
	this.player1.sprintUsed = false;
	this.player2.sprintUsed = false;
    }
}