package se.yrgo.jumpyduke.player;

import se.yrgo.jumpyduke.utils.GameModeState;

public class Player {
    private String userName;
    private int lastScore;
    private GameModeState gameModeState;
    private int highScore;
    private int rounds;

    public Player() {
        rounds = 0;
    }

    public Player(String userName, int lastScore, int highScore, int rounds, GameModeState gameModeState) {
        this.userName = userName;
        this.lastScore = lastScore;
        this.highScore = highScore;
        this.rounds = rounds;
        this.gameModeState = gameModeState;
    }

    public GameModeState getGameModeState() {
        return gameModeState;
    }

    public void setGameModeState(GameModeState gameModeState) {
        this.gameModeState = gameModeState;
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