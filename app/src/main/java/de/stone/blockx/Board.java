package de.stone.blockx;

import de.stone.blockx.shapes.Shape;
import de.stone.blockx.shapes.ShapeFactory;

public class Board {
    private int[][] board;
    private final int width, height;

    private Shape currentShape;
    private int currentShapeX = 0, currentShapeY = 0;

    private final ShapeFactory shapeFactory = new ShapeFactory();

    private Board(final int width, final int height) {
        this.width = width;
        this.height = height;
        board = new int[height][width];
        try {
            addNewShape();
        } catch (final GameOverException e) {
            // will never happen ;)
        }
    }

    public static Board getNewInstance(final int width, final int height) {
        return new Board(width, height);
    }

    public int moveShapeDown() throws GameOverException {
        int removedLines = 0;
        pickupShape();
        currentShapeY++;
        if (currentShapeY + currentShape.getHeight() <= height) {
            if (isBlocked()) {
                currentShapeY--;
                applyShape();
                removedLines = removeLines();
                addNewShape();
            } else {
                applyShape();
            }
        } else {
            currentShapeY--;
            applyShape();
            removedLines = removeLines();
            addNewShape();
        }

        return removedLines;
    }

    public void moveShapeLeft() {
        pickupShape();
        currentShapeX--;
        if (currentShapeX >= 0) {
            if (isBlocked()) {
                currentShapeX++;
            }
            applyShape();
        } else {
            currentShapeX++;
            applyShape();
        }
    }

    public void moveShapeRight() {
        pickupShape();
        currentShapeX++;
        if (currentShapeX + currentShape.getWidth() <= width) {
            if (isBlocked()) {
                currentShapeX--;
            }
            applyShape();
        } else {
            currentShapeX--;
            applyShape();
        }
    }

    public void rotateRight() {
        pickupShape();
        currentShape.rotateRight();
        if (isBlocked()) {
            rotateLeft();
        }
        applyShape();
    }

    public void rotateLeft() {
        currentShape.rotateLeft();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private Shape getNextShape() {
        return shapeFactory.getShape();
    }

    private void addNewShape() throws GameOverException {
        currentShape = getNextShape();
        currentShapeX = (width / 2) - (currentShape.getWidth() / 2);
        currentShapeY = 0;

        if (isBlocked()) {
            throw new GameOverException();
        }

        applyShape();
    }

    private int removeLines() {
        int lines = 0;
        for (int i = currentShapeY; i < currentShapeY + currentShape.getHeight(); i++) {
            boolean isFull = true;
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) {
                    isFull = false;
                    lines = 0;
                    break;
                }
            }

            if (isFull) {
                removeLine(i);
                lines++;
            }
        }

        return lines;
    }

    private void removeLine(final int toRemove) {
        final int[][] newBoard = new int[height][width];

        boolean found = false;
        for (int i = height - 1; i > -1; i--) {
            if (i == toRemove) {
                found = true;
            }

            if (found) {
                if (i - 1 > 0) {
                    System.arraycopy(board, i - 1, newBoard, i, 1);
                }
            } else {
                System.arraycopy(board, i, newBoard, i, 1);
            }
        }

        board = newBoard;
    }

    private boolean isBlocked() {
        int shapeWidth = 0, shapeHeight = 0;

        for (int i = currentShapeX; i < currentShapeX + currentShape.getWidth(); i++) {
            for (int j = currentShapeY; j < currentShapeY + currentShape.getHeight(); j++) {
                if (i >= width || i < 0 || j >= height || j < 0) {
                    return true;
                }

                if (board[j][i] != 0 && currentShape.getValue(shapeWidth, shapeHeight) != 0) {
                    return true;
                }
                shapeHeight++;
            }
            shapeWidth++;
            shapeHeight = 0;
        }

        return false;
    }

    private void applyShape() {
        int shapeWidth = 0, shapeHeight = 0;
        for (int i = currentShapeX; i < currentShapeX + currentShape.getWidth(); i++) {
            for (int j = currentShapeY; j < currentShapeY + currentShape.getHeight(); j++) {
                if (board[j][i] == 0) {
                    board[j][i] = currentShape.getValue(shapeWidth, shapeHeight);
                }
                shapeHeight++;
            }
            shapeWidth++;
            shapeHeight = 0;
        }
    }

    private void pickupShape() {
        int shapeWidth = 0, shapeHeight = 0;
        for (int i = currentShapeX; i < currentShapeX + currentShape.getWidth(); i++) {
            for (int j = currentShapeY; j < currentShapeY + currentShape.getHeight(); j++) {
                if (currentShape.getValue(shapeWidth, shapeHeight) != 0) {
                    board[j][i] = 0;
                }

                shapeHeight++;
            }
            shapeWidth++;
            shapeHeight = 0;
        }
    }

    public Shape nextShapePreview() {
        return shapeFactory.nextShapePreview();
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buffer.append(board[i][j] + " ");
            }
            buffer.append("\n");
        }

        return buffer.toString();
    }
}
