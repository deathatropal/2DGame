package basics;

public class GameThread extends Thread
{
	private boolean running;
	private long threadStarted;
	private Screen screen;
	private long startTime;
	private long timeDiff;
	private long endTime;
	private final int MAX_FPS = 120;
	private final int SKIP_TICKS = 1000 / MAX_FPS;
	
	public GameThread(Screen screen)
	{
		threadStarted = System.currentTimeMillis();
		this.screen = screen;
		endTime = 0;
	}
	
	public void run()
	{
		int sleep_time = 0;
		int next_game_tick = (int) getTickCount();
		while(running)
		{
			startTime = System.currentTimeMillis();
			timeDiff = startTime - endTime;
			screen.update(timeDiff);
			screen.repaint();
			next_game_tick += SKIP_TICKS;
			sleep_time = next_game_tick - (int) getTickCount();
			if(sleep_time > 0)
			{
				try 
				{
					sleep(sleep_time);
				} 
				catch (InterruptedException e) 
				{
					
				}
			}
			endTime = startTime;
		}
	}
	
	public void setRunning(boolean running)
	{
		this.running = running;
	}
	
	public long getTickCount()
	{
		return System.currentTimeMillis() - threadStarted;
	}
}
