package de.stone.blockx;

import org.junit.Test;

import de.stone.blockx.Board;
import de.stone.blockx.GameOverException;

public class BoardTest
{
	@Test
	public void gameOverTest() throws GameOverException
	{
		final Board board = Board.getNewInstance(10, 18);

		for (int i = 0; i < 100; i++)
		{
			board.moveShapeDown();
			System.out.println(board.toString());
		}
	}

	@Test
	public void moveLeft()
	{
		final Board board = Board.getNewInstance(10, 18);

		for (int i = 0; i < 10; i++)
		{
			board.moveShapeLeft();
			System.out.println(board.toString());
		}
	}

	@Test
	public void moveRight()
	{
		final Board board = Board.getNewInstance(10, 18);

		for (int i = 0; i < 10; i++)
		{
			board.moveShapeRight();
			System.out.println(board.toString());
		}
	}
}
