package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Block extends Coordinates
{
	public Image image;
	public Rectangle blockRect;
	
	public Block(int x, int y, int imageHeight, int imageWidth)
	{
		this.x = x;
		this.y = y;
		blockRect = new Rectangle(x, y, imageWidth, imageHeight);
	}
	
	public boolean canMoveY(Player player, int imageHeight, int imageWidth, double futureMoveY)
	{
		Rectangle futureRect = new Rectangle(player.x, (int) futureMoveY, player.imageWidth(), player.imageHeight());
		if(futureRect.intersects(blockRect))
		{
			return false;
		}
		return true;
	}
	
	public boolean canMoveX(Player player, int imageHeight, int imageWidth, double futureMoveX)
	{
		Rectangle futureRect = new Rectangle((int) futureMoveX, player.y, player.imageWidth(), player.imageHeight());
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
}
