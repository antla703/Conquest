package se.liu.ida.antla703.tddd78.conquest;

/**
 * Player class consisting of information specific to the player.
 */

public class Player
{
    /** Field specifying wether the powerup "battle cry" has been used or not. */
    public boolean battlecryUsed = false;

    /** Field specifying wether the powerup "sprint" has been used or not. */
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
