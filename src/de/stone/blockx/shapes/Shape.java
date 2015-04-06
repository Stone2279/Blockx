package de.stone.blockx.shapes;

import java.util.ArrayList;
import java.util.List;

public class Shape
{
	private int currentState = 0;
	protected List<int[][]> states = new ArrayList<int[][]>();

	public Shape()
	{
	}

	public Shape(final Shape shape)
	{
		states = shape.states;
		currentState = 0;
	}

	public void rotateLeft()
	{
		if (currentState - 1 < 0)
		{
			currentState = states.size() - 1;
		} else
		{
			currentState--;
		}
	}

	public void rotateRight()
	{
		if (currentState + 1 >= states.size())
		{
			currentState = 0;
		} else
		{
			currentState++;
		}
	}

	public int[][] getPoints()
	{
		return states.get(currentState);
	}

	public int getHeight()
	{
		return states.get(currentState)[0].length;
	}

	public int getWidth()
	{
		return states.get(currentState).length;
	}

	public int[] getLastRow()
	{
		final int[][] state = states.get(currentState);
		return state[state[0].length];
	}

	public int getValue(final int x, final int y)
	{
		return states.get(currentState)[x][y];
	}

	@Override
	public String toString()
	{
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < states.get(currentState).length; i++)
		{
			for (int j = 0; j < states.get(currentState)[i].length; j++)
			{
				buffer.append(states.get(currentState)[i][j] + " ");
			}
			buffer.append("\n");
		}

		return buffer.toString();
	}
}
