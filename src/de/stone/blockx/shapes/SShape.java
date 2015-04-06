package de.stone.blockx.shapes;

public class SShape extends Shape
{
	public SShape()
	{
		states.add(new int[][] { { 0, 5, 5 }, { 5, 5, 0 } });
		states.add(new int[][] { { 5, 0 }, { 5, 5 }, { 0, 5 } });
	}
}
