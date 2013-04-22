package basics;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Start extends JFrame
{
	/**
	 * Starts the game
	 * Edits the screen
	 **/
	private static Screen screen;
	private static GameThread thread;
	private static FileManagement fileManage = new FileManagement("src/saves/saveddata.dat.encrypted", "src/saves/saveddata.dat.decrypted");
	private static SaveData save;
	private static String key = "!$D°ì7ð~";
	private static int iterations = 1;
	private static FileInputStream fileDecryptedI = null;
	private static FileInputStream fileEncryptedI = null;
	private static FileOutputStream fileDecryptedO = null;
	private static FileOutputStream fileEncryptedO = null;
	
	//Changes the screen of the game and adds a new Screen()
	public Start(int width, int height)
	{
		System.out.println("Loading save...");
		//Setting the encrypted and decrypted files
		try 
		{
			fileEncryptedI = new FileInputStream("src/saves/saveddata.dat.encrypted");
		} 
		catch (FileNotFoundException e2) 
		{
			e2.printStackTrace();
		}
		
		try 
		{
			fileDecryptedO = new FileOutputStream("src/saves/saveddata.dat.decrypted");
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		
		//If the encrypted file is not empty
		if(!fileManage.isEmpty("src/saves/saveddata.dat.encrypted"))
		{
			//Decrypting the encrypted file
			try 
			{
				fileManage.decrypt(key, fileEncryptedI, fileDecryptedO);
			} 
			catch (Throwable e) 
			{
				e.printStackTrace();
			}
			
			save = fileManage.readFile("src/saves/saveddata.dat.decrypted");
			
			//Clearing the data in the decrypted file
			RandomAccessFile clearData = null;
			
			try 
			{
				clearData = new RandomAccessFile("src/saves/saveddata.dat.decrypted", "rw");
				clearData.setLength(0);
				clearData.close();
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			//If file encrypted is empty then set save to this
			save = new SaveData(0, 0, 0);
		}
		
		screen = new Screen(width, height, save.currentLevel);
		add(screen);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/res/nolegs.png"));
		Image image = icon.getImage();
		Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "Custom cursor");
		this.getContentPane().setCursor(customCursor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	//Creates a new JFrame called start, the two numbers are the size of the screen (width and height)
	public static void main(String args[])
	{
		System.out.println("Starting!");
		new Start(1000, 1000);
		thread = new GameThread(screen);
		thread.setRunning(true);
		thread.start();
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				System.out.println("Saving Data...");
				save = new SaveData(screen.level, screen.player.x, screen.player.y);
				fileManage.writeFile(save, "src/saves/saveddata.dat.decrypted");
				
				try
				{
					fileDecryptedI = new FileInputStream("src/saves/saveddata.dat.decrypted");
				} 
				catch (FileNotFoundException e2) 
				{
					e2.printStackTrace();
				}
				
				try 
				{
					fileEncryptedO = new FileOutputStream("src/saves/saveddata.dat.encrypted");
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
				
				save = new SaveData(screen.level, screen.player.getX(), screen.player.getY());
				
				try 
				{
					fileManage.encrypt(key, fileDecryptedI, fileEncryptedO);
				} 
				catch (Throwable e) 
				{
					e.printStackTrace();
				}
				
				//Clearing the data in the decrypted file
				RandomAccessFile clearData = null;
				
				try 
				{
					clearData = new RandomAccessFile("src/saves/saveddata.dat.decrypted", "rw");
					clearData.setLength(0);
					clearData.close();
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
