package de.stone.blockx.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import de.stone.blockx.GameSpeed;
import de.stone.blockx.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.startGame)).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    final Intent intent = new Intent(getBaseContext(), GameActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        final SeekBar seekBar = ((SeekBar) findViewById(R.id.level));
        seekBar.setProgress(GameSpeed.getCurrentLevel());
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(final SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
                GameSpeed.setCurrentLevel(progress);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
