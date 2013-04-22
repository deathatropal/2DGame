package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Block extends Coordinates
{
	public Image image;
	
	public Block(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean canMoveY(Player player, int imageHeight, int imageWidth, double futureMoveY)
	{
		if(player.getCenterX() >= x && player.getCenterX() <= x + imageWidth)
		{
			if(player.y > y + imageHeight && futureMoveY + player.imageHeight() <= y)
			{
				return false;
			}
			if(player.y < y + imageHeight && futureMoveY + player.imageHeight() >= y + imageHeight)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean canMoveX(Player player, int imageHeight, int imageWidth, double futureMoveX)
	{
		if(player.getCenterY() >= y && player.getCenterY() <= y + imageHeight)
		{
			if(player.x > x + imageWidth && futureMoveX + player.imageWidth() <= x)
			{
				return false;
			}
			if(player.x < x + imageWidth && futureMoveX + player.imageWidth() >= x + imageWidth)
			{
				return false;
			}
		}
		return true;
	}
	
	public void draw(Graphics graphics, Screen screen)
	{
		Graphics2D graphics2 = (Graphics2D) graphics;
		graphics2.drawImage(image, x, y, screen);
	}
}
