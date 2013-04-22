package basics;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends Block
{
	private int height;
	private int width;
	private String wallRes = "/res/wall.png";
	private String wallCornerRes = "/res/wallcorner.png";
	private String wallRotatedRes = "/res/wallrotated.png";
	private Image wallCorner;
	private Image wallSide;
	private Image wallRotated;
	
	public Wall(int x, int y, int width, int height, boolean cornertrue, boolean rotated) 
	{
		super(x, y);
		this.width = width;
		this.height = height;
		ImageIcon corner = new ImageIcon(this.getClass().getResource(wallCornerRes));
		ImageIcon wall = new ImageIcon(this.getClass().getResource(wallRes));
		ImageIcon rWall = new ImageIcon(this.getClass().getResource(wallRotatedRes));
		wallCorner = corner.getImage();
		wallSide = wall.getImage();
		wallRotated = rWall.getImage();
		wallCorner = wallCorner.getScaledInstance((int) (width * 1.5/ 125), (int) (height * 1.5/ 125), Image.SCALE_DEFAULT);
		wallSide = wallSide.getScaledInstance((int) (width * 1.5/ 125), (int) (height * 1.5/ 125), Image.SCALE_DEFAULT);
		wallRotated = wallRotated.getScaledInstance((int) (width * 1.5/ 125), (int) (height * 1.5/ 125), Image.SCALE_DEFAULT);
		if(cornertrue)
		{
			image = wallCorner;
		}
		else
		{
			if(rotated)
			{
				image = wallRotated;
			}
			else
			{
				image = wallSide;
			}
		}
	}
	
	public int imageWidth()
	{
		return (int) (width * 1.5/ 125);
	}
	
	public int imageHeight()
	{
		return (int) (height * 1.5/ 125);
	}
}
