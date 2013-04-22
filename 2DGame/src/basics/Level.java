package basics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Level 
{
	private int levelWidth;
	private int levelHeight;
	private int playerX;
	private int playerY;
	private String levelToLoad;
	
	public Level(int level)
	{
		try 
		{
			load("src/levels/level" + level + ".dat");
			levelToLoad = "src/levels/level" + level + ".dat";
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void load(String level_name) throws IOException
	{
		levelWidth = Integer.parseInt(getLineInfo(0, level_name)); //First line of the file
		levelHeight = Integer.parseInt(getLineInfo(1, level_name)); //Second line
		playerX = Integer.parseInt(getLineInfo(2, level_name)); //Third
		playerY = Integer.parseInt(getLineInfo(3, level_name)); //Fourth
	}
	
	private String getLineInfo(int line, String level_name) throws IOException
	{
		FileInputStream in = null;
		try 
		{
			in = new FileInputStream(level_name);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		for(int i = 0; i < line; ++i)
		{
			reader.readLine();
		}
		return reader.readLine();
	}
	
	public int getLevelWidth()
	{
		return levelWidth;
	}
	
	public int getLevelHeight()
	{
		return levelHeight;
	}
	
	public int getPlayerX()
	{
		return playerX;
	}
	
	public int getPlayerY()
	{
		return playerY;
	}
	
	public LinkedList getInfo()
	{
		LinkedList list = new LinkedList();
		
		try 
		{
			for(int i = 4; getLineInfo(i, levelToLoad) != null; i++) //Skips the first five lines
			{
				String[] lineInfo = getLineInfo(i, levelToLoad).split(";");
				for(int j = 0; j < lineInfo.length; j++)
				{
					list.add(lineInfo[j]);
				}
				list.add(" ");
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return list;
	}
}
