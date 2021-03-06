package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Floor extends Scenery
{
	private static int width;
	private static int height;
	private static int scale;
	
	private static double thirtyTwo;
	private static double eight;
	private static double sixteen;
	private static double twentyFour;
	
	private Image image;
	private Image sideWalkImg;
	private Image fancyGroundImg;
	private Image tileWhiteImg;
	private Image tileBlackImg;
	private Image tiledDoorImg;
	private Image tiledDoorRotatedImg;
	private Image fancyGroundEndImg;
	private Image lightWoodImg;
	private Image grateImg;
	
	private String sideWalkRes = "/res/sidewalkfull.png";
	private String fancyRes = "/res/fancyground.png";
	private String tileWhiteRes = "/res/tiledWhite.png";
	private String tileBlackRes= "/res/tiledBlack.png";
	private String tiledDoorRes = "/res/tileddoor.png";
	private String tiledDoorRotatedRes = "/res/tileddoorrotated.png";
	private String fancyGroundEndRes = "/res/fancygroundend.png";
	private String lightWoodRes = "/res/lightwood.png";
	private String grateRes = "/res/grate.png";
	
	public Floor(int x, int y, int width, int height, int floorPicture, int scale)
	{
		super(x, y);
		
		this.width = width;
		this.height = height;
		this.scale = scale;
		
		eight = 125;
		sixteen = 62.5;
		twentyFour = 41.6;
		thirtyTwo = 31.2;
		
		ImageIcon sideWalkIcon = new ImageIcon(this.getClass().getResource(sideWalkRes));
		ImageIcon fancyGroundIcon = new ImageIcon(this.getClass().getResource(fancyRes));
		ImageIcon tileBlackIcon = new ImageIcon(this.getClass().getResource(tileBlackRes));
		ImageIcon tileWhiteIcon = new ImageIcon(this.getClass().getResource(tileWhiteRes));
		ImageIcon tiledDoorIcon = new ImageIcon(this.getClass().getResource(tiledDoorRes));
		ImageIcon tiledDoorRotIcon = new ImageIcon(this.getClass().getResource(tiledDoorRotatedRes));
		ImageIcon fancyGroundEndIcon = new ImageIcon(this.getClass().getResource(fancyGroundEndRes));
		ImageIcon lightWoodIcon = new ImageIcon(this.getClass().getResource(lightWoodRes));
		ImageIcon grateIcon = new ImageIcon(this.getClass().getResource(grateRes));
		
		sideWalkImg = sideWalkIcon.getImage();
		fancyGroundImg = fancyGroundIcon.getImage();
		tileWhiteImg = tileWhiteIcon.getImage();
		tileBlackImg = tileBlackIcon.getImage();
		tiledDoorImg = tiledDoorIcon.getImage();
		tiledDoorRotatedImg = tiledDoorRotIcon.getImage();
		fancyGroundEndImg = fancyGroundEndIcon.getImage();
		lightWoodImg = lightWoodIcon.getImage();
		grateImg	 = grateIcon.getImage();
		
		if(floorPicture == 0)
		{
			image = sideWalkImg;
		}
		else if(floorPicture == 1)
		{
			image = fancyGroundImg;
		}
		else if(floorPicture == 2)
		{
			image = tileWhiteImg;
		}
		else if(floorPicture == 3)
		{
			image = tileBlackImg;
		}
		else if(floorPicture == 4)
		{
			image = tiledDoorImg;
		}
		else if(floorPicture == 5)
		{
			image = tiledDoorRotatedImg;
		}
		else if(floorPicture == 6)
		{
			image = fancyGroundEndImg;
		}
		else if(floorPicture == 7)
		{
			image = lightWoodImg;
		}
		else if(floorPicture == 8)
		{
			image = grateImg;
		}
		
		
		
		if(floorPicture == 0)
		{
			image = image.getScaledInstance((int) (width * scale / twentyFour), (int) (height * scale/ twentyFour), Image.SCALE_DEFAULT);
		}
		else
		{
			image = image.getScaledInstance((int) (width * scale/ eight), (int) (height * scale/ eight), Image.SCALE_DEFAULT);
		}
	}
	
	public static int imageWidth()
	{
		return (int) (width * scale/ eight);
	}
	
	public static int imageHeight()
	{
		return (int) (height * scale/ eight);
	}
	
	public void draw(Graphics graphics, Screen screen)
	{
		Graphics2D graphics2 = (Graphics2D) graphics;
		graphics2.drawImage(image, x, y, screen);
	}
}
