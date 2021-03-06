package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Player extends Entity
{
	private String playerRightLead = "/playerres/playerleadright.png";
	private String playerLeftLead = "/playerres/playerleadleft.png";
	private String playerCentered = "/playerres/playercentered.png";
	private String firstPunch = "/playerres/playerpunchingright.png";
	private String secondPunch = "/playerres/playerpunchingleft.png";
	private Image playerRightImg;
	private Image playerLeftImg;
	private Image playerCenterImg;
	private Image punchImg;
	private Image punch2Img;
	private long attackTime;
	private boolean rightLastHit;
	private Legs legs;
	private int lastAngle;
	private boolean moving;
	private long animationTime;
	private boolean punching;
	private double angle;
	
	public Player(int x, int y, double speed, int width, int height)
	{
		ImageIcon playerIcon1 = new ImageIcon(this.getClass().getResource(playerRightLead));
		ImageIcon playerIcon2 = new ImageIcon(this.getClass().getResource(playerLeftLead));
		ImageIcon playerIcon3 = new ImageIcon(this.getClass().getResource(playerCentered));
		ImageIcon punch1 = new ImageIcon(this.getClass().getResource(firstPunch));
		ImageIcon punch2 = new ImageIcon(this.getClass().getResource(secondPunch));
		punchImg = punch1.getImage();
		punch2Img = punch2.getImage();
		playerRightImg = playerIcon1.getImage();
		playerLeftImg = playerIcon2.getImage();
		playerCenterImg = playerIcon3.getImage();
		playerRightImg = playerRightImg.getScaledInstance((int) (width * 1.5/ 27), (int) (height * 1.5/ 30.3), Image.SCALE_DEFAULT);
		playerLeftImg = playerLeftImg.getScaledInstance((int) (width * 1.5/ 27), (int) (height * 1.5/ 30.3), Image.SCALE_DEFAULT);
		playerCenterImg = playerCenterImg.getScaledInstance((int) (width * 1.5/ 27), (int) (height * 1.5/ 30.3), Image.SCALE_DEFAULT);
		punchImg = punchImg.getScaledInstance((int) (width * 1.5 / 27), (int) (height * 1.5/ 30.3), Image.SCALE_DEFAULT);
		punch2Img = punch2Img.getScaledInstance((int) (width * 1.5 / 27), (int) (height * 1.5/ 30.3), Image.SCALE_DEFAULT);
		image = playerCenterImg;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
		rightLastHit = true;
		legs = new Legs(x, y, width, height);
	}
	
	public void move(long timeDiff, int levelWidth, int levelHeight, BlockList block)
	{
		timeDiff = timeDiff / 4;
		if((int) x + (dx * speed * timeDiff) != x ||(int) y + (dy * speed * timeDiff) != y)
		{
			if((x + (dx * speed * timeDiff) < levelWidth) && (x + (dx * speed * timeDiff) > 0))
			{
				if(block.canMoveX(this, x + (dx * speed * timeDiff)))
				{
					x += dx * speed * timeDiff;
				}
			}
			if((y + (dy * speed * timeDiff) < levelHeight) && (y + (dy * speed * timeDiff) > 0))
			{
				if(block.canMoveY(this, y + (dy * speed * timeDiff)))
				{
					y += dy * speed * timeDiff;
				}
			}
			legs.move(x, y);
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
	
	//The attack animation for player
	public boolean attack(long timeDiff, EnemyList list)
	{
		int reach = (int) (height * 1.5 / 125); 
		Point hitPoint = new Point((int) (getCenterX() + (reach * Math.cos(angle))), (int) (getCenterY() + (reach * Math.sin(angle))));
		list.checkForHit(hitPoint);
		if(attackTime == 0)
		{
			punching = true;
			if(rightLastHit)
			{
				image = punchImg;
				rightLastHit = false;
			}
			else
			{
				image = punch2Img;
				rightLastHit = true;
			}
		}
		attackTime = attackTime + timeDiff;
		if(attackTime > 200)
		{
			image = playerCenterImg;
			attackTime = 0;
			punching = false;
			return true;
		}
		return false;
	}
	
	public void walk(long timeDiff)
	{
		legs.walk(timeDiff);
	}
	
	public void setMoving(boolean moving)
	{
		this.moving = moving;
		legs.setMoving(moving);
	}
	
	//drawing the legs of the player and rotating them depending on which way the player is moving
	public void drawLegs(Graphics graphics, Screen screen)
	{
		int centerY = y + (imageHeight() / 2);
		int centerX = x + (imageWidth() / 2);
		Graphics2D graphics2 = (Graphics2D) graphics;
		if(dx == -1 && dy == 0)
		{
			//rotating the legs to the left
			((Graphics2D)graphics).rotate(Math.toRadians(270), centerX, centerY);
			lastAngle = 270;
		}
		if(dx == 1 && dy == 0)
		{
			//rotating the legs to the right
			((Graphics2D)graphics).rotate(Math.toRadians(90), centerX, centerY);
			lastAngle = 90;
		}
		if(dx == 0 && dy == -1)
		{
			//rotating the legs back to their normal position
			((Graphics2D)graphics).rotate(Math.toRadians(0), centerX, centerY);
			lastAngle = 0;
		}
		if(dx == 0 && dy == 1)
		{
			//rotating the legs backwards
			((Graphics2D)graphics).rotate(Math.toRadians(180), centerX, centerY);
			lastAngle = 180;
		}
		if(dx == -1 && dy == -1)
		{
			//rotating the legs between the left and forward position
			((Graphics2D)graphics).rotate(Math.toRadians(315), centerX, centerY);
			lastAngle = 315;
		}
		if(dx == 1 && dy == -1)
		{
			//rotating the legs between the right and forwards position
			((Graphics2D)graphics).rotate(Math.toRadians(45), centerX, centerY);
			lastAngle = 45;
		}
		if(dx == 1 && dy == 1)
		{
			//rotating the legs between the right and backwards position
			((Graphics2D)graphics).rotate(Math.toRadians(135), centerX, centerY);
			lastAngle = 135;
		}
		if(dx == -1 && dy == 1)
		{
			//rotating the legs between the left and backwards position
			((Graphics2D)graphics).rotate(Math.toRadians(225), centerX, centerY);
			lastAngle = 225;
		}
		if(moving == false)
		{
			((Graphics2D)graphics).rotate(Math.toRadians(lastAngle), centerX, centerY);
		}
        graphics2.drawImage(legs.getImage(), (int) (x + ((imageWidth() / 2) - (legs.imageWidth() / 2))), (int) (y + ((imageHeight() / 2) - (legs.imageHeight() / 2))), screen);
	}
	
	public void drawPlayer(Graphics graphics, Screen screen, int mouseX, int mouseY)
	{
		int centerY = getCenterY();
		int centerX = getCenterX();
		angle = Math.atan2(centerY - mouseY, centerX - mouseX) - Math.PI / 2;
        Graphics2D graphics2 = (Graphics2D) graphics;
        ((Graphics2D)graphics).rotate(angle, centerX, centerY);
        graphics2.drawImage(image, x, y, screen);
	}
	
	public boolean animationDone()
	{
		if(legs.animationDone() == false)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void animateImg(long timeDiff)
	{
		if(punching == false)
		{
			if(moving == false && animationTime > 0)
			{
				animationTime = animationTime - timeDiff;
			}
			else
			{
				animationTime = animationTime + timeDiff;
			}
			if(animationTime < 0)
			{
				animationTime = 0;
			}
			if(animationTime > 400)
			{
				animationTime = 0;
			}
			if(animationTime <= 0)
			{
				image = playerCenterImg;
			}
			if(animationTime > 100 && animationTime < 200)
			{
				image = playerRightImg;
			}
			if(animationTime > 200 && animationTime < 300)
			{
				image = playerCenterImg;
			}
			if(animationTime > 300 && animationTime < 400)
			{
				image = playerLeftImg;
			}
		}
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
