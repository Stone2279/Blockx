package de.stone.blockx;

public class GameSpeed {
    private static int totalNumberOfLines = 0;
    private static int currentLevel = 0;

    public static int getGameSpeed(final int numberOfLines) {
        totalNumberOfLines += numberOfLines;

        if (totalNumberOfLines >= (currentLevel + 1) * 10 && currentLevel < 10) {
            currentLevel++;
        }

        switch (currentLevel) {
            case 0:
                return 800;
            case 1:
                return 700;
            case 2:
                return 600;
            case 3:
                return 500;
            case 4:
                return 400;
            case 5:
                return 300;
            case 6:
                return 200;
            case 7:
                return 150;
            case 8:
                return 100;
            case 9:
                return 75;
            default:
                return 800;
        }
    }

    public static void reset() {
        GameSpeed.totalNumberOfLines = 0;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(final int currentLevel) {
        GameSpeed.currentLevel = currentLevel;
    }

}
