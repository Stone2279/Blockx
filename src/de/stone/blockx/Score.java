package de.stone.blockx;

public class Score
{
	private int score = 0;

	public void calculateScore(final int removedLines)
	{
		switch (removedLines)
		{
		case 1:
			score += 40 * (GameSpeed.getCurrentLevel() + 1);
			break;
		case 2:
			score += 100 * (GameSpeed.getCurrentLevel() + 1);
			break;
		case 3:
			score += 300 * (GameSpeed.getCurrentLevel() + 1);
			break;
		case 4:
			score += 1200 * (GameSpeed.getCurrentLevel() + 1);
			break;
		default:
			break;
		}
	}

	public int getScore()
	{
		return score;
	}
}
