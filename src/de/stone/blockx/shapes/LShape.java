package de.stone.blockx.shapes;

public class LShape extends Shape
{

	public LShape()
	{
		states.add(new int[][] { { 3, 0 }, { 3, 0 }, { 3, 3 } });
		states.add(new int[][] { { 3, 3, 3 }, { 3, 0, 0 } });
		states.add(new int[][] { { 3, 3 }, { 0, 3 }, { 0, 3 } });
		states.add(new int[][] { { 0, 0, 3 }, { 3, 3, 3 } });
	}
}
