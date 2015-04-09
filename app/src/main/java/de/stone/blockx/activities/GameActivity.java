package de.stone.blockx.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

import de.stone.blockx.R;
import de.stone.blockx.Sound;
import de.stone.blockx.view.GameView;

public class GameActivity extends Activity {
    private GameView gameView;
    private final Sound sound = new Sound();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = ((GameView) findViewById(R.id.gameView));

        ((ImageButton) findViewById(R.id.left)).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    gameView.moveLeft();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    gameView.moveLeftReleased();
                }
                return true;
            }
        });

        ((ImageButton) findViewById(R.id.right)).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    gameView.moveRight();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    gameView.moveRightReleased();
                }
                return true;
            }
        });

        ((ImageButton) findViewById(R.id.rotateRight)).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    gameView.rotateRight();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    gameView.rotateRightReleased();
                }
                return true;
            }
        });

        ((ImageButton) findViewById(R.id.down)).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    gameView.moveDown();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    gameView.moveDownReleased();
                }
                return true;
            }
        });

        gameView.init();
        sound.playGameMusic(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        destroyGameView();
        moveTaskToBack(true);
    }

    @Override
    protected void onDestroy() {
        destroyGameView();
        sound.release();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        destroyGameView();
        super.onBackPressed();
    }

    private void destroyGameView() {
        sound.stopMusic();
        gameView.destroy();
        finish();
    }
}
