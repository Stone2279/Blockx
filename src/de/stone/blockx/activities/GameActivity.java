package de.stone.blockx.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import de.stone.blockx.R;
import de.stone.blockx.Sound;
import de.stone.blockx.view.GameView;

public class GameActivity extends Activity
{
	private GameView gameView;
	private final Sound sound = new Sound();

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		setContentView(R.layout.activity_game);

		gameView = ((GameView) findViewById(R.id.gameView));

		((ImageButton) findViewById(R.id.left)).setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(final View v, final MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					gameView.moveLeft();
				}
				return true;
			}
		});

		((ImageButton) findViewById(R.id.right)).setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(final View v, final MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					gameView.moveRight();
				}
				return true;
			}
		});

		((ImageButton) findViewById(R.id.rotateRight)).setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(final View v, final MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					gameView.rotateRight();
				}
				return true;
			}
		});

		((ImageButton) findViewById(R.id.down)).setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(final View v, final MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					gameView.moveDown();
				}
				return true;
			}
		});

		gameView.init();
		sound.playGameMusic(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPause()
	{
		sound.stopMusic();
		sound.release();
		gameView.destroy();
		super.onPause();

	}

	@Override
	protected void onResume()
	{
		sound.playGameMusic(this);
		super.onResume();
	}

	@Override
	protected void onDestroy()
	{
		sound.stopMusic();
		sound.release();
		gameView.destroy();
		super.onDestroy();
	}
}
