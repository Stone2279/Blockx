package de.stone.blockx;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class Sound {
    private MediaPlayer mediaPlayer;

    public void playMusic(final Context context, final int soundId) {
        stopMusic();
        mediaPlayer = MediaPlayer.create(context, soundId);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void playGameMusic(final Context context) {
        playMusic(context, R.raw.music);
    }

    public void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
