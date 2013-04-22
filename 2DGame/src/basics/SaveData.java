package basics;

import java.io.Serializable;

public class SaveData implements Serializable
{
	/**
	 * Serializing data!
	 */
	public int currentLevel;
	public int playerX;
	public int playerY;
	
	public SaveData(int currentLevel, int playerX, int playerY)
	{
		this.currentLevel = currentLevel;
		this.playerX = playerX;
		this.playerY = playerY;
	}
}
