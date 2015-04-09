package de.stone.blockx.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import de.stone.blockx.Board;
import de.stone.blockx.GameLoopThread;
import de.stone.blockx.GameSpeed;
import de.stone.blockx.Score;
import de.stone.blockx.graphics.BoardDrawer;

public class GameView extends SurfaceView implements Callback {

    private SurfaceHolder surfaceHolder;
    private GameLoopThread gameLoopThread;
    private BoardDrawer boardDrawer;
    private Board board;
    private Score score;

    @SuppressLint("WrongCall")
    public GameView(final Context context) {
        super(context);
    }

    public GameView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init() {
        surfaceHolder = getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(final SurfaceHolder holder) {
                gameLoopThread.setRunning(false);
            }

            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                boardDrawer = new BoardDrawer(getContext());
                board = Board.getNewInstance(10, 18);
                score = new Score();
                gameLoopThread = new GameLoopThread(GameView.this);
                gameLoopThread.start();
                gameLoopThread.setRunning(true);

            }

            @Override
            public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {

            }
        });
    }

    public void destroy() {
        if (gameLoopThread != null) {
            boolean retry = true;
            gameLoopThread.setRunning(false);
            while (retry) {
                try {
                    gameLoopThread.join();
                    retry = false;
                } catch (final InterruptedException e) {

                }
            }
        }
    }

    @Override
    public void onDraw(final Canvas canvas) {
        boardDrawer.draw(canvas, board);
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

    public void moveLeft() {
        gameLoopThread.setMoveLeft(true);
    }

    public void moveLeftReleased() {
        gameLoopThread.setMoveLeft(false);
    }

    public void moveRight() {
        gameLoopThread.setMoveRight(true);
    }

    public void moveRightReleased() {
        gameLoopThread.setMoveRight(false);
    }

    public void moveDown() {
        gameLoopThread.setMoveDown(true);
    }

    public void moveDownReleased() {
        gameLoopThread.setMoveDown(false);
    }

    public void rotateRight() {
        gameLoopThread.setRotateRight(true);
    }

    public void rotateRightReleased() {
        gameLoopThread.setRotateRight(false);
    }

    public void rotateLeft() {
        gameLoopThread.setRotateLeft(true);
    }

    public void rotateLeftReleased() {
        gameLoopThread.setRotateLeft(false);
    }

    public void updateScore(final int removedLines) {
        score.calculateScore(removedLines);
        boardDrawer.updateScore(score.getScore());
    }

    public void gameOver() {
        GameSpeed.reset();
        final Activity activity = (Activity) getContext();
        activity.finish();
    }
}
