package conquest;

import java.awt.*;

public class Scout extends AbstractSquare {

    private Color color;

    public Scout(int player){

	super(player, 1, 1, 3);

	if (player == 0){

	    throw new IllegalArgumentException("Invalid player: 0 (neutral)");
	}

	this.color = this.setColor();
    }

    private Color setColor(){
	int player = this.getPlayer();

	if (player == 1){
	    return Color.CYAN;
	}
	else{
	    return Color.BLUE;
	}
    }

    /**public Color getColor(){
	return this.color;
    }**/

    @Override public void draw(final Graphics g, int x, int y) {

    	g.setColor(color);
	int size = ConquestComponent.getSquareSize();
    	g.fillRect(x * size, y * size, size, size);
	g.setColor(Color.black);
	g.drawRect(x * size, y * size, size, size);
        }
}
