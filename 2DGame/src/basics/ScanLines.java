package basics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ScanLines
{
	private String scanLinesRes = "/res/scanlines.png";
	private Image scanLinesImg;
	private Image image;
	
	public ScanLines(int width, int height, double scale)
	{
		ImageIcon scanLinesIcon = new ImageIcon(this.getClass().getResource(scanLinesRes));
		scanLinesImg = scanLinesIcon.getImage();
		image = scanLinesImg;
		image = image.getScaledInstance((int) (width), (int) (height), Image.SCALE_DEFAULT);
	}
	
	public void draw(Graphics graphics, Screen screen, int newX, int newY)
	{
		Graphics2D graphics2 = (Graphics2D) graphics;
		graphics2.drawImage(image, newX, newY, screen);
	}
}
