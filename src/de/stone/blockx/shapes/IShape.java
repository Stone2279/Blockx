package de.stone.blockx.shapes;


public class IShape extends Shape
{
	public IShape()
	{
		states.add(new int[][] { { 1 }, { 1 }, { 1 }, { 1 } });
		states.add(new int[][] { { 1, 1, 1, 1 } });
	}
}
