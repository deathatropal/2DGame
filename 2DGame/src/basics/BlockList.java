package basics;

import java.awt.Graphics;
import java.util.LinkedList;

public class BlockList 
{
	private LinkedList<Block> blocks;
	private Wall wall;
	
	public BlockList(Level loader, int width, int height)
	{
		blocks = new LinkedList();
		wall = new Wall(0, 0, width, height, false, false);
		load(loader, width, height);
	}
	
	public void update(long timeDiff)
	{
		
	}
	
	public void draw(Graphics graphics, Screen screen)
	{
		for(Block block : blocks)
		{
			block.draw(graphics, screen);
		}
	}
	
	public boolean canMoveX(Player player, double futureMoveX)
	{
		for(Block block : blocks)
		{
			if(!block.canMoveX(player, wall.imageWidth(), wall.imageWidth(), futureMoveX))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean canMoveY(Player player, double futureMoveY)
	{
		for(Block block : blocks)
		{
			if(!block.canMoveY(player, wall.imageWidth(), wall.imageWidth(), futureMoveY))
			{
				return false;
			}
		}
		return true;
	}
	
	private void load(Level loader, int width, int height)
	{
		LinkedList list = loader.getInfo();
		int y = 1250;
		int x = 1250 + wall.imageWidth();
		
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).equals("w"))
			{
				blocks.add(new Wall(x, y, width, height, false, false));
			}
			if(list.get(i).equals("r"))
			{
				blocks.add(new Wall(x, y, width, height, false, true));
			}
			if(list.get(i).equals("c"))
			{
				blocks.add(new Wall(x, y, width, height, true, false));
			}
			if(list.get(i).equals("e"))
			{
				x = 1250;
				y = y + wall.imageHeight();
			}
			else
			{
				x = x + wall.imageWidth();
			}
		}
	}
}