package basics;

import java.awt.Image;

public class Entity extends Coordinates
{
	public int dx;
	public int dy;
	public double speed;
	public Image image;
	public int width;
	public int height;
	public boolean dead;
	
	public Image getImage()
	{
		return image;
	}
	
	public void goLeft()
	{
		dx = -1;
	}
	public void goRight()
	{
		dx = 1;
	}
	
	public void goUp()
	{
		dy = -1;
	}
	
	public void goDown()
	{
		dy = 1;
	}
	
	public void stopY()
	{
		dy = 0;
	}
	
	public void stopX()
	{
		dx = 0;
	}
	
	public int getXDirrection()
	{
		return dx;
	}
	
	public int getYDirrection()
	{
		return dy;
	}
	
	public void setDead()
	{
		dead = true;
	}
}
