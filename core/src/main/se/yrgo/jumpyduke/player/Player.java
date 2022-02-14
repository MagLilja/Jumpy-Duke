package se.yrgo.jumpyduke.player;

public class Player {
    private static String userName;
    private static int lastScore;
    private static int highScore;

    public static void setUserName(String userName) {
        Player.userName = userName;
    }

    public static void setLastScore(int lastScore) {
        Player.lastScore = lastScore;
    }

    public static void setHighScore(int highScore) {
        Player.highScore = highScore;
    }

    public static String getUserName() {
        return userName;
    }

    public static int getLastScore() {
        return lastScore;
    }

    public static int getHighScore() {
        return highScore;
    }
}
