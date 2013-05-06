package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Mouse extends Coordinates
{
	private Image image;
	private Image mouseImage;
	private Image mouseImage1;
	private Image mouseImage2;
	private Image mouseImage3;
	private long animateTime;
	
	public Mouse(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/mouseres/mouse.png"));
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/mouseres/mouse1.png"));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("/mouseres/mouse2.png"));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("/mouseres/mouse3.png"));
		mouseImage = icon.getImage();
		mouseImage1 = icon1.getImage();
		mouseImage2 = icon2.getImage();
		mouseImage3 = icon3.getImage();
		mouseImage = mouseImage.getScaledInstance((int) (height * 3/ 200), (int) (width * 3/ 200), Image.SCALE_DEFAULT);
		mouseImage1 = mouseImage1.getScaledInstance((int) (height * 3/ 200), (int) (width * 3/ 200), Image.SCALE_DEFAULT);
		mouseImage2 = mouseImage2.getScaledInstance((int) (height * 3/ 200), (int) (width * 3/ 200), Image.SCALE_DEFAULT);
		mouseImage3 = mouseImage3.getScaledInstance((int) (height * 3/ 200), (int) (width * 3/ 200), Image.SCALE_DEFAULT);
		image = mouseImage;
	}
	
	public void move(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void drawMouse(Graphics graphics, Screen screen, int editX, int editY)
	{
        Graphics2D graphics2 = (Graphics2D) graphics;
        graphics2.drawImage(image, x - editX, y - editY, screen);
	}
	
	public void animateMouse(long timeDiff)
	{
		animateTime = animateTime + timeDiff;
		if(animateTime == 0)
		{
			image = mouseImage;
		}
		if(animateTime > 75 && animateTime < 150)
		{
			image = mouseImage1;
		}
		if(animateTime > 150 && animateTime < 225)
		{
			image = mouseImage2;
		}
		if(animateTime > 225 && animateTime < 300)
		{
			image = mouseImage3;
		}
		if(animateTime > 300)
		{
			animateTime = 0;
		}
	}
}
