package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Block extends Coordinates
{
	public Image image;
	public Rectangle futureRect;
	public Rectangle blockRect;
	
	public Block(int x, int y, int imageWidth, int imageHeight)
	{
		this.x = x;
		this.y = y;
		blockRect = new Rectangle(x, y, imageWidth, imageHeight);
	}
	
	public boolean canMoveY(Player player, double futureMoveY)
	{
		futureRect = new Rectangle(player.x, (int) futureMoveY, player.imageWidth(), player.imageHeight());
		if(futureRect.intersects(blockRect))
		{
			return false;
		}
		return true;
	}
	
	public boolean canMoveX(Player player, double futureMoveX)
	{
		futureRect = new Rectangle((int) futureMoveX, player.y, player.imageWidth(), player.imageHeight());
		if(futureRect.intersects(blockRect))
		{
			return false;
		}
		return true;
	}
	
	public void draw(Graphics graphics, Screen screen)
	{
		Graphics2D graphics2 = (Graphics2D) graphics;
		graphics2.drawImage(image, x, y, screen);
	}
	
	public void setRect(int imageWidth, int imageHeight)
	{
		blockRect = new Rectangle(x, y, imageWidth, imageHeight);
	}
	
	public Rectangle getRect()
	{
		return blockRect;
	}
}
