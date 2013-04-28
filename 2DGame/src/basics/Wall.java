package basics;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends Block
{
	private static int height;
	private static int width;
	private static double scale;
	
	private String wallRes = "/res/wall.png";
	private String wallCornerRes = "/res/wallcorner.png";
	private String wallRotatedRes = "/res/wallrotated.png";
	private String brickWindowRes = "/res/brickwindow.png";
	private String brickWindowRotatedRes = "/res/brickwindowrotated.png";
	private String wallNormalRes = "/res/wallnormal.png";
	private String wallNormalRotatedRes = "/res/wallnormalrotated.png";
	private String wallNormalCornerRes = "/res/wallnormalcorner.png";
	private String lampRes = "/res/lamp.png";
	
	private Image wallCorner;
	private Image wallSide;
	private Image wallRotated;
	private Image brickWindowImg;
	private Image brickWindowRotatedImg;
	private Image wallNormalImg;
	private Image wallNormalRotatedImg;
	private Image wallNormalCornerImg;
	private Image lampImg;
	
	public Wall(int x, int y, int width, int height, int wallImage, int scale) 
	{
		super(x, y, imageWidth(), imageHeight());
		this.width = width;
		this.height = height;
		this.scale = scale;
		ImageIcon corner = new ImageIcon(this.getClass().getResource(wallCornerRes));
		ImageIcon wall = new ImageIcon(this.getClass().getResource(wallRes));
		ImageIcon rWall = new ImageIcon(this.getClass().getResource(wallRotatedRes));
		ImageIcon brickWindowIcon = new ImageIcon(this.getClass().getResource(brickWindowRes));
		ImageIcon brickWindowRotIcon = new ImageIcon(this.getClass().getResource(brickWindowRotatedRes));
		ImageIcon wallNormalIcon = new ImageIcon(this.getClass().getResource(wallNormalRes));
		ImageIcon wallNormalRotatedIcon = new ImageIcon(this.getClass().getResource(wallNormalRotatedRes));
		ImageIcon wallNormalCornerIcon = new ImageIcon(this.getClass().getResource(wallNormalCornerRes));
		ImageIcon lampIcon = new ImageIcon(this.getClass().getResource(lampRes));
		wallCorner = corner.getImage();
		wallSide = wall.getImage();
		wallRotated = rWall.getImage();
		brickWindowImg = brickWindowIcon.getImage();
		brickWindowRotatedImg = brickWindowRotIcon.getImage();
		wallNormalImg = wallNormalIcon.getImage();
		wallNormalRotatedImg = wallNormalRotatedIcon.getImage();
		wallNormalCornerImg = wallNormalCornerIcon.getImage();
		lampImg = lampIcon.getImage();
		if(wallImage == 1)
		{
			image = wallCorner;
		}
		else if(wallImage == 2)
		{
			image = wallRotated;
		}
		else if(wallImage == 0)
		{
			image = wallSide;
		}
		else if(wallImage == 3)
		{
			image = brickWindowImg;
		}
		else if(wallImage == 4)
		{
			image = brickWindowRotatedImg;
		}
		else if(wallImage == 5)
		{
			image = wallNormalImg;
		}
		else if(wallImage == 6)
		{
			image = wallNormalRotatedImg;
		}
		else if(wallImage == 7)
		{
			image = wallNormalCornerImg;
		}
		else if(wallImage == 8)
		{
			image = lampImg;
		}
		if(wallImage == 3)
		{
			image = image.getScaledInstance((int) (width * scale / 125), (int) (height * scale/ 31.25), Image.SCALE_DEFAULT);
		}
		else if(wallImage == 4)
		{
			image = image.getScaledInstance((int) (width * scale / 31.25), (int) (height * scale/ 125), Image.SCALE_DEFAULT);
		}
		else
		{
			image = image.getScaledInstance((int) (width * scale/ 125), (int) (height * scale/ 125), Image.SCALE_DEFAULT);
		}
	}
	
	public static int imageWidth()
	{
		return (int) (width * scale/ 125);
	}
	
	public static int imageHeight()
	{
		return (int) (height * scale/ 125);
	}
}
