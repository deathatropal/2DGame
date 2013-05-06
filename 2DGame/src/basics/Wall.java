package basics;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends Block
{
	private static int height;
	private static int width;
	private static int wallImage;
	private static double scale;
	private static double thirtyTwo;
	private static double eight;
	private static double sixteen;
	private static double twentyFour;
	
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
		this.wallImage = wallImage;
		
		eight = 125;
		sixteen = 62.5;
		twentyFour = 41.6;
		thirtyTwo = 31.2;
		
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
			image = image.getScaledInstance((int) (width * scale / 71.4), (int) (height * scale/ thirtyTwo), Image.SCALE_DEFAULT);
		}
		else if(wallImage == 4)
		{
			image = image.getScaledInstance((int) (width * scale / thirtyTwo), (int) (height * scale/ 71.4), Image.SCALE_DEFAULT);
		}
		else if(wallImage == 0)
		{
			image = image.getScaledInstance((int) (width * scale/ eight), (int) (height * scale/ 71.4), Image.SCALE_DEFAULT);
		}
		else if(wallImage == 5)
		{
			image = image.getScaledInstance((int) (width * scale/ eight), (int) (height * scale/ 90.9), Image.SCALE_DEFAULT);
		}
		else if(wallImage == 2)
		{
			image = image.getScaledInstance((int) (width * scale/ 71.4), (int) (height * scale/ eight), Image.SCALE_DEFAULT);
		}
		else if(wallImage == 6)
		{
			image = image.getScaledInstance((int) (width * scale/ 90.9), (int) (height * scale/ eight), Image.SCALE_DEFAULT);
		}
		else if(wallImage == 7)
		{
			image = image.getScaledInstance((int) (width * scale/ 90.9), (int) (height * scale/ 90.9), Image.SCALE_DEFAULT);
		}
		else if(wallImage == 1)
		{
			image = image.getScaledInstance((int) (width * scale/ 71.4), (int) (height * scale/ 71.4), Image.SCALE_DEFAULT);
		}
	}
	
	public static int imageWidth()
	{
		return (int) (width * scale / eight);
	}
	
	public static int imageHeight()
	{
		return (int) (height * scale / eight);
	}
	
	public static int imageWidthModified()
	{
		if(wallImage == 4)
		{
			return (int) (width * scale/ thirtyTwo);
		}
		else
		{
			return (int) (width * scale/ eight);
		}
	}
	
	public static int imageHeightModified()
	{
		if(wallImage == 3)
		{
			return (int) (height * scale/ thirtyTwo);
		}
		else
		{
			return (int) (height * scale/ eight);
		}
	}
}
