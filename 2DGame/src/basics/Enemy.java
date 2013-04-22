package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy extends Entity
{
	private Image enemyCentered;
	private String enemyImg = "/res/playercentered.png";
	private AIMethods AI;
	
	public Enemy(int x, int y, int speed, int width, int height)
	{
		AI = new AIMethods();
		ImageIcon enemyIcon1 = new ImageIcon(this.getClass().getResource(enemyImg));
		enemyCentered = enemyIcon1.getImage();
		enemyCentered = enemyCentered.getScaledInstance((int) (width * 1.5/ 27), (int) (height * 1.5/ 30.3), Image.SCALE_DEFAULT);
		image = enemyCentered;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void move(long timeDiff)
	{
		timeDiff = timeDiff / 3;
		x += dx * speed * timeDiff;
		y += dy * speed * timeDiff;
	}
	
	public void draw(Graphics graphics, Screen screen)
	{
		Graphics2D graphics2 = (Graphics2D) graphics;
        graphics2.drawImage(image, x, y, screen);
	}
	
	public void checkForPlayer(Player player)
	{
		if(Math.sqrt((player.getCenterX() - getCenterX())^2 + (player.getCenterY() - getCenterY())^2) == 10)
		{
			AI.getPath();
		}
	}
	
	public int imageWidth()
	{
		return (int) (width * 1.5/ 27);
	}
	
	public int imageHeight()
	{
		return (int) (height * 1.5/ 30.3);
	}	
	
	public int getCenterX()
	{
		return x + (imageWidth() / 2);
	}
	
	public int getCenterY()
	{
		return y + (imageHeight() / 2);
	}
}
