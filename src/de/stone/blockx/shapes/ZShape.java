package de.stone.blockx.shapes;

public class ZShape extends Shape
{
	public ZShape()
	{
		states.add(new int[][] { { 7, 7, 0 }, { 0, 7, 7 } });
		states.add(new int[][] { { 0, 7 }, { 7, 7 }, { 7, 0 } });
	}
}
