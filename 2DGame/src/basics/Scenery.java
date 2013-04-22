package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Scenery extends Coordinates
{
	private Image image;
	
	public Scenery(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics graphics, Screen screen)
	{
        Graphics2D graphics2 = (Graphics2D) graphics;
        graphics2.drawImage(image, x, y, screen);
	}
}
