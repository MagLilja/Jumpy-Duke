package se.yrgo.jumpyduke.player;

public class Player {
    private String userName;
    private int lastScore;
    private int highScore;
    private int rounds;

    public Player() {
        rounds = 0;
    }

    public Player(String userName, int lastScore, int highScore, int rounds) {
        this.userName = userName;
        this.lastScore = lastScore;
        this.highScore = highScore;
        this.rounds = rounds;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
        setHighScore();
    }

    public int getHighScore() {
        return highScore;
    }

    private void setHighScore() {
        if (lastScore > this.highScore) {
            highScore = lastScore;
        }
    }

    public int getRounds() {
        return rounds;
    }

    public void incrementRounds() {
        rounds++;
    }
}