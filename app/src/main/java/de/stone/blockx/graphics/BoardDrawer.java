package de.stone.blockx.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;

import de.stone.blockx.Board;
import de.stone.blockx.GameSpeed;
import de.stone.blockx.R;
import de.stone.blockx.shapes.Shape;

public class BoardDrawer {
    private int board_width;
    private int board_height;
    private int border_width;

    private int color_border;
    private int color_blue;
    private int color_orange;
    private int color_yellow;
    private int color_red;
    private int color_green;
    private int color_magenta;
    private int color_cyan;

    private final float squareSize = 28;

    private Paint borderPaint = null;
    private Paint stoneBorderPaint = null;
    private Paint stonePaint = null;
    private Paint line = null;
    private final Paint scorePaint;

    private int score = 0;

    private final Context context;

    public BoardDrawer(final Context context) {
        this.context = context;
        init();

        stoneBorderPaint = new Paint();
        stoneBorderPaint.setAntiAlias(true);
        stoneBorderPaint.setStyle(Style.STROKE);
        stoneBorderPaint.setStrokeWidth(0);
        stoneBorderPaint.setColor(Color.BLACK);

        stonePaint = new Paint();
        stonePaint.setAntiAlias(true);
        stonePaint.setStyle(Style.FILL);

        borderPaint = new Paint();
        borderPaint.setColor(color_border);
        borderPaint.setStyle(Style.STROKE);
        borderPaint.setStrokeWidth(border_width);
        borderPaint.setAntiAlias(true);

        line = new Paint();
        line.setColor(color_border);
        line.setAntiAlias(true);

        scorePaint = new Paint();
        scorePaint.setAntiAlias(true);
        scorePaint.setTextSize(30);
        scorePaint.setColor(Color.WHITE);
    }

    public void draw(final Canvas canvas, final Board board) {
        if (canvas != null) {
            canvas.drawColor(0, Mode.CLEAR);
            drawBoard(canvas, board);
            drawNextShape(canvas, board);
            drawScore(canvas);
        }
    }

    private void drawBoard(final Canvas canvas, final Board board) {
        final float boardX = 5;
        final float boardY = 5;
        final float width = (squareSize * board_width) + boardX + border_width;
        final float height = (squareSize * board_height) + boardY + border_width;

        for (int i = 1; i < board_width; i++) {
            final float x = i * squareSize + boardX + border_width / 2;
            canvas.drawLine(x, boardY, x, height, line);
        }

        for (int i = 1; i < board_height; i++) {
            final float y = i * squareSize + boardY + border_width / 2;
            canvas.drawLine(boardX, y, width, y, line);
        }

        canvas.drawRect(boardX, boardY, width, height, borderPaint);

        final int[][] matrix = board.getBoard();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                final int value = matrix[i][j];
                if (value != 0) {
                    setPaintColor(value);

                    final float x = (i * squareSize) + 6 + border_width / 2;
                    final float y = (j * squareSize) + 6 + border_width / 2;
                    canvas.drawRect(y - 1, x - 1, y + squareSize - 2, x + squareSize - 2, stonePaint);
                }
            }
        }
    }

    public void drawNextShape(final Canvas canvas, final Board board) {
        final float x = (squareSize * board_width) + border_width + 35;
        final float y = 30;

        final Shape preview = board.nextShapePreview();
        for (int i = 0; i < preview.getWidth(); i++) {
            for (int j = 0; j < preview.getHeight(); j++) {
                final int value = preview.getValue(i, j);
                if (value != 0) {
                    setPaintColor(value);

                    final float shapeX = y + (j * squareSize) + border_width / 2;
                    final float shapeY = x + (i * squareSize) + border_width / 2;
                    canvas.drawRect(shapeY - 1, shapeX - 1, shapeY + squareSize - 2, shapeX + squareSize - 2, stonePaint);
                }
            }
        }
    }

    private void drawScore(final Canvas canvas) {
        final float x = (squareSize * board_width) + border_width + 35;
        final float y = 250;

        canvas.drawText("Punkte", x, 215, scorePaint);
        canvas.drawText(String.valueOf(score), x, y, scorePaint);

        canvas.drawText("Level", x, 315, scorePaint);
        canvas.drawText(String.valueOf(GameSpeed.getCurrentLevel()), x, 350, scorePaint);

    }

    public void updateScore(final int score) {
        this.score = score;
    }

    private void setPaintColor(final int color) {
        switch (color) {
            case (1):
                stonePaint.setColor(color_blue);
                break;
            case (2):
                stonePaint.setColor(color_orange);
                break;
            case (3):
                stonePaint.setColor(color_yellow);
                break;
            case (4):
                stonePaint.setColor(color_red);
                break;
            case (5):
                stonePaint.setColor(color_green);
                break;
            case (6):
                stonePaint.setColor(color_magenta);
                break;
            case (7):
                stonePaint.setColor(color_cyan);
                break;
            default:
                stonePaint.setColor(Color.BLACK);
                break;
        }
    }

    private void init() {
        board_width = context.getResources().getInteger(R.integer.board_width);
        board_height = context.getResources().getInteger(R.integer.board_height);
        border_width = context.getResources().getInteger(R.integer.border_width);

        color_border = context.getResources().getColor(R.color.border);
        color_blue = context.getResources().getColor(R.color.square_blue);
        color_orange = context.getResources().getColor(R.color.square_orange);
        color_yellow = context.getResources().getColor(R.color.square_yellow);
        color_red = context.getResources().getColor(R.color.square_red);
        color_green = context.getResources().getColor(R.color.square_green);
        color_magenta = context.getResources().getColor(R.color.square_magenta);
        color_cyan = context.getResources().getColor(R.color.square_cyan);
    }
}
