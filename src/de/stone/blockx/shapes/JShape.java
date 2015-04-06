package de.stone.blockx.shapes;

public class JShape extends Shape
{

	public JShape()
	{
		states.add(new int[][] { { 0, 2 }, { 0, 2 }, { 2, 2 } });
		states.add(new int[][] { { 2, 0, 0 }, { 2, 2, 2 } });
		states.add(new int[][] { { 2, 2 }, { 2, 0 }, { 2, 0 } });
		states.add(new int[][] { { 2, 2, 2 }, { 0, 0, 2 } });
	}

}
