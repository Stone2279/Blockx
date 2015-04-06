package de.stone.blockx;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import de.stone.blockx.view.GameView;

public class GameLoopThread extends Thread
{
	static final int FPS = R.integer.fps;
	private final GameView gameView;
	private boolean isRunning = false;

	private boolean moveLeft = false;
	private boolean moveRight = false;
	private boolean moveDown = false;

	private boolean rotateRight = false;
	private boolean rotateLeft = false;

	public GameLoopThread(final GameView theView)
	{
		gameView = theView;
	}

	public void setRunning(final boolean run)
	{
		isRunning = run;
	}

	@SuppressLint("WrongCall")
	@Override
	public void run()
	{
		final long TPS = 1000 / FPS;
		long startTime, sleepTime;
		long nextDrop = System.currentTimeMillis() + GameSpeed.getGameSpeed(0);

		while (isRunning)
		{
			Canvas canvas = null;
			startTime = System.currentTimeMillis();
			try
			{
				canvas = gameView.getHolder().lockCanvas();
				synchronized (gameView.getHolder())
				{
					gameView.onDraw(canvas);
				}
			} finally
			{
				if (canvas != null)
				{
					gameView.getHolder().unlockCanvasAndPost(canvas);
				}
			}
			sleepTime = TPS - (System.currentTimeMillis() - startTime);
			try
			{
				if (sleepTime > 0)
				{
					sleep(sleepTime);
				} else
				{
					sleep(10);
				}
			} catch (final Exception e)
			{

			}

			if (moveLeft)
			{
				gameView.getBoard().moveShapeLeft();
				moveLeft = false;
			}

			if (moveRight)
			{
				gameView.getBoard().moveShapeRight();
				moveRight = false;
			}

			if (rotateRight)
			{
				gameView.getBoard().rotateRight();
				rotateRight = false;
			}

			if (rotateLeft)
			{
				gameView.getBoard().rotateLeft();
				rotateLeft = false;
			}

			if (startTime > nextDrop || moveDown)
			{
				try
				{
					final int removedLines = gameView.getBoard().moveShapeDown();
					// GameSpeed.getGameSpeed(level, numberOfLines)
					gameView.updateScore(removedLines);
					nextDrop = startTime + GameSpeed.getGameSpeed(removedLines);
					moveDown = false;
				} catch (final GameOverException e)
				{
					isRunning = false;
					gameView.gameOver();
				}
			}
		}
	}

	public void setMoveLeft(final boolean moveLeft)
	{
		this.moveLeft = moveLeft;
	}

	public void setMoveRight(final boolean moveRight)
	{
		this.moveRight = moveRight;
	}

	public void setMoveDown(final boolean moveDown)
	{
		this.moveDown = moveDown;
	}

	public void setRotateRight(final boolean rotateRight)
	{
		this.rotateRight = rotateRight;
	}

	public void setRotateLeft(final boolean rotateLeft)
	{
		this.rotateLeft = rotateLeft;
	}

}