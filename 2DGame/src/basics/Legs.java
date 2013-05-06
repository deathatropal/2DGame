package basics;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Legs extends Coordinates
{
	private Image image;
	private Image slr; //size of leg, name, right or left
	private Image sll;
	private Image llr;
	private Image lll;
	private Image nl;
	private Image tlr;
	private Image tll;
	private String shortlegright = "/playerres/smallplayerlegsrightlead.png";
	private String shortlegleft = "/playerres/smallplayerlegsleftlead.png";
	private String longlegright = "/playerres/playerlegsrightlead.png";
	private String longlegleft = "/playerres/playerlegsleftlead.png";
	private String tinylegright = "/playerres/tinylegsrightlead.png";
	private String tinylegleft = "/playerres/tinylegsleftlead.png";
	private String nolegs = "/playerres/nolegs.png";
	private int width;
	private int height;
	private boolean shortLegs;
	private long walkTime;
	private boolean right;
	private boolean tinyLegs;
	private boolean moving;
	private boolean animationFinished;
	
	public Legs(int x, int y, int width, int height)
	{
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource(shortlegright));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource(shortlegleft));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource(longlegright));
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource(longlegleft));
		ImageIcon icon5 = new ImageIcon(this.getClass().getResource(tinylegright));
		ImageIcon icon6 = new ImageIcon(this.getClass().getResource(tinylegleft));
		ImageIcon icon7 = new ImageIcon(this.getClass().getResource(nolegs));
		slr = icon1.getImage();
		sll = icon2.getImage();
		llr = icon3.getImage();
		lll = icon4.getImage();
		tlr = icon5.getImage();
		tll = icon6.getImage();
		nl = icon7.getImage();
		slr = slr.getScaledInstance((int) (width * 1.5 / 50), (int) (height * 1.5 / 31.3), Image.SCALE_DEFAULT);
		sll = sll.getScaledInstance((int) (width * 1.5 / 50), (int) (height * 1.5 / 31.3), Image.SCALE_DEFAULT);
		llr = llr.getScaledInstance((int) (width * 1.5 / 45.5), (int) (height * 1.5 / 25), Image.SCALE_DEFAULT);
		lll = lll.getScaledInstance((int) (width * 1.5 / 45.5), (int) (height * 1.5 / 25), Image.SCALE_DEFAULT);
		tlr = tlr.getScaledInstance((int) (width * 1.5 / 50), (int) (height * 1.5 / 41.7), Image.SCALE_DEFAULT);
		tll = tll.getScaledInstance((int) (width * 1.5 / 50), (int) (height * 1.5 / 41.7), Image.SCALE_DEFAULT);
		image = nl;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		right = true;
		animationFinished = true;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void move(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int imageHeight()
	{
		if(tinyLegs)
		{
			return (int) (height * 1.5 / 41.7);
		}
		else
		{
			if(shortLegs)
			{
				return (int) (height * 1.5 / 31.3);
			}
			else
			{
				return (int) (height * 1.5 / 25);
			}
		}
	}
	
	public int imageWidth()
	{
		if(tinyLegs)
		{
			return (int) (width * 1.5 / 50);
		}
		else
		{
			if(shortLegs)
			{
				return (int) (width * 1.5 / 50);
			}
			else
			{
				return (int) (width * 1.5 / 45.5);
			}
		}
	}
	
	public void walk(long timeDiff)
	{
		if(moving == true)
		{
			walkTime = walkTime + timeDiff;
		}
		if(moving == false && walkTime > 0)
		{
			walkTime = walkTime - timeDiff;
		}
		if(walkTime < 0)
		{
			walkTime = 0;
		}
		if(walkTime <= 0)
		{
			shortLegs = false;
			tinyLegs = false;
			animationFinished = true;
			image = nl;
		}
		if(walkTime > 100 && walkTime < 200)
		{
			tinyLegs = true;
			shortLegs = false;
			animationFinished = false;
			if(right)
			{
				image = tlr;
			}
			else
			{
				image = tll;
			}
		}
		if(walkTime > 200 && walkTime < 300)
		{
			tinyLegs = false;
			shortLegs = true;
			if(right)
			{
				image = slr;
			}
			else
			{
				image = sll;
			}
		}
		if(walkTime > 300 && walkTime < 400)
		{
			shortLegs = false;
			tinyLegs = false;
			if(right)
			{
				image = llr;
			}
			else
			{
				image = lll;
			}
		}
		if(walkTime > 400 && walkTime < 500)
		{
			shortLegs = true;
			tinyLegs = false;
			if(right)
			{
				image = slr;
			}
			else
			{
				image = sll;
			}
		}
		if(walkTime > 500 && walkTime < 600)
		{
			shortLegs = false;
			tinyLegs = true;
			if(right)
			{
				image = tlr;
			}
			else
			{
				image = tll;
			}
		}
		if(walkTime > 600)
		{
			if(right)
			{
				right = false;
			}
			else
			{
				right = true;
			}
			walkTime = 0;
		}
	}
	
	public void setMoving(boolean moving)
	{
		this.moving = moving;
	}
	
	public boolean animationDone()
	{
		return animationFinished;
	}
}
