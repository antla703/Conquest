package conquest;

import java.awt.*;

public class Empty extends AbstractSquare
{
    private Color color = Color.lightGray;

    public Empty(){

	super(0, 1, 1, 0);

    }

    /**public Color getColor(){
	return this.color;
    }**/

    @Override public void draw(final Graphics g, int x, int y) {

    	g.setColor(this.color);
	int size = ConquestComponent.getSquareSize();
    	g.fillRect(x*size, y*size, size, size);
	g.setColor(Color.black);
	g.drawRect(x * size, y * size, size, size);

        }
}

