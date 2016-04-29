package conquest;

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
    private Player currentPlayer = Player.PLAYER1;
    private Square active = null;
    private Point activePos = null;
    private int activeMovement;
    private boolean selecting = true;
    private CollisionHandler collisionHandler = new DefaultCollisionHandler();
    private boolean battlecryUsedP1 = false;
    private boolean battlecryUsedP2 = false;
    private boolean sprintUsedP1 = false;
    private boolean sprintUsedP2 = false;
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
	setSquare(0, 0, new Scout(Player.PLAYER1));
	setSquare(2, 0, new Knight(Player.PLAYER1));
	setSquare(4, 0, new Champion(Player.PLAYER1));
	setSquare(6, 0, new Knight(Player.PLAYER1));
	setSquare(8, 0, new Scout(Player.PLAYER1));
	setSquare(1, 1, new Soldier(Player.PLAYER1));
	setSquare(3, 1, new Scout(Player.PLAYER1));
	setSquare(5, 1, new Scout(Player.PLAYER1));
	setSquare(7, 1, new Soldier(Player.PLAYER1));

	setSquare(0, 8, new Scout(Player.PLAYER2));
	setSquare(2, 8, new Knight(Player.PLAYER2));
	setSquare(4, 8, new Champion(Player.PLAYER2));
	setSquare(6, 8, new Knight(Player.PLAYER2));
	setSquare(8, 8, new Scout(Player.PLAYER2));
	setSquare(1, 7, new Soldier(Player.PLAYER2));
	setSquare(3, 7, new Scout(Player.PLAYER2));
	setSquare(5, 7, new Scout(Player.PLAYER2));
	setSquare(7, 7, new Soldier(Player.PLAYER2));

	this.notifyListeners();
    }

    public int getWidth() {
        return this.squares.length-2;
    }

    public int getHeight() {
        return this.squares[0].length-2;
    }

    public int getCurrentPlayerInt(){

	if (this.currentPlayer == Player.PLAYER1){
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

    private Player getPlayer(int x, int y) {

	Square square = getSquare(x,y);

	return square.getPlayer();

    }

    public void setActive(Point point){

	if (selecting){

	    int x = (int)point.getX();
	    int y = (int)point.getY();

	    if (getPlayer(x, y) == this.currentPlayer){

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

    private boolean isPlayer(int x, int y, Player player){
	if(getPlayer(x, y) == player){
	    return true;
	}

	else{
	    return false;
	}

    }

    protected boolean isEnemy(int x, int y){
	if (isPlayer(x, y, this.currentPlayer)){
	    return false;
	}

	else{
	    return !isEmpty(x, y) && !isOutside(x, y);
	}
    }

    protected boolean isEmpty(int x, int y) {
        if (this.isPlayer(x, y, Player.EMPTY)) {
            return true;
        }
        return false;
    }

    private boolean isOutside(int x, int y) {
        if (this.isPlayer(x, y, Player.OUTSIDE)) {
            return true;
        }
        return false;
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
	if (this.currentPlayer == Player.PLAYER1 && !this.battlecryUsedP1 && !this.selecting){
	    this.active.battlecry();
	    this.battlecryUsedP1 = true;
	}
	else if (this.currentPlayer == Player.PLAYER2 && !this.battlecryUsedP2 && !this.selecting){
	    this.active.battlecry();
	    this.battlecryUsedP2 = true;
	}

	this.notifyListeners();
    }

    public void sprint(){
	if (this.currentPlayer == Player.PLAYER1 && !this.sprintUsedP1 && !this.selecting){
	    this.activeMovement += 1;
	    this.sprintUsedP1 = true;
	}
	else if (this.currentPlayer == Player.PLAYER2 && !this.sprintUsedP2 && !this.selecting){
	    this.activeMovement += 1;
	    this.sprintUsedP2 = true;
	}
    }

    public void moveLeft(){

	if (this.existsActive() && !this.selecting && !this.collisionHandler.hasCollision(-1, 0, this)){
	    setSquare(this.activePos.x -1, this.activePos.y, active);
	    setSquare(this.activePos.x, this.activePos.y, new Empty());
	    this.activePos.x -= 1;

	    if (this.activeMovement <= 1) {
		this.toggleMove();
	    }

	    this.activeMovement -= 1;
	    this.notifyListeners();

	}

    }

    public void moveRight(){

	if (this.existsActive() && !this.selecting && !this.collisionHandler.hasCollision(1, 0, this)){
	    setSquare(this.activePos.x +1, this.activePos.y, active);
	    setSquare(this.activePos.x, this.activePos.y, new Empty());
	    this.activePos.x += 1;

	    if (this.activeMovement <= 1) {
		this.toggleMove();
	    }

	    this.activeMovement -= 1;
	    this.notifyListeners();

	}

    }

    public void moveDown(){

	if (this.existsActive() && !this.selecting && !this.collisionHandler.hasCollision(0, 1, this)){
	    setSquare(this.activePos.x, this.activePos.y +1, active);
	    setSquare(this.activePos.x, this.activePos.y, new Empty());
	    this.activePos.y += 1;

	    if (this.activeMovement <= 1) {
		this.toggleMove();
	    }

	    this.activeMovement -= 1;
	    this.notifyListeners();
	}

    }

    public void moveUp(){
	if (this.existsActive() && !this.selecting && !this.collisionHandler.hasCollision(0, -1, this)){
	    setSquare(this.activePos.x, this.activePos.y -1, active);
	    setSquare(this.activePos.x, this.activePos.y, new Empty());
	    this.activePos.y -= 1;

	    if (this.activeMovement <= 1) {
		this.toggleMove();
	    }

	    this.activeMovement -= 1;
	    this.notifyListeners();
	}

    }

    public void toggleMove(){

	if (this.selecting && this.existsActive()){
	    this.selecting = false;
	}

	else if (!this.selecting && this.existsActive()){
	    this.selecting = true;

	    if (this.collisionHandler.hasEnemy(1, 0, this)){
		Square enemy = this.getSquare(this.activePos.x + 1, this.activePos.y);
		this.active.takeDamage(enemy.getDamage());
		enemy.takeDamage(this.active.getDamage());
	    }

	    else if (this.collisionHandler.hasEnemy(0, 1, this)){
		Square enemy = this.getSquare(this.activePos.x, this.activePos.y + 1);
		this.active.takeDamage(enemy.getDamage());
		enemy.takeDamage(this.active.getDamage());
	    }

	    else if (this.collisionHandler.hasEnemy(-1, 0, this)){
		Square enemy = this.getSquare(this.activePos.x - 1, this.activePos.y);
		this.active.takeDamage(enemy.getDamage());
		enemy.takeDamage(this.active.getDamage());
	    }

	    else if (this.collisionHandler.hasEnemy(0, -1, this)){
		Square enemy = this.getSquare(this.activePos.x, this.activePos.y - 1);
		this.active.takeDamage(enemy.getDamage());
		enemy.takeDamage(this.active.getDamage());
	    }

	    this.clearDead();

	    if (this.currentPlayer == Player.PLAYER1){
		this.checkWin(Player.PLAYER2);
		this.currentPlayer = Player.PLAYER2;
	    }

	    else {
		this.checkWin(Player.PLAYER1);
		this.currentPlayer = Player.PLAYER1;
	    }
	}

    }

    private void checkWin(Player player){
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
	this.selecting = true;
	this.currentPlayer = Player.PLAYER1;
	this.active = null;
	this.activePos = null;
	this.activeMovement = 0;
	this.battlecryUsedP1 = false;
	this.battlecryUsedP2 = false;
	this.sprintUsedP1 = false;
	this.sprintUsedP2 = false;
    }
}