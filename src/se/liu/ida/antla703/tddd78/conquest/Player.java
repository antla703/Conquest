package se.liu.ida.antla703.tddd78.conquest;

public class Player
{
    public boolean battlecryUsed = false;
    public boolean sprintUsed = false;
    private PlayerType playerType;

    public Player(PlayerType player)
    {
 	this.playerType = player;
    }

    public PlayerType getPlayerType(){
	return this.playerType;
    }
}
