package se.yrgo.jumpyduke.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int playerId;
    private String userName;
    private int lastScore;
    private int highScore;
    private int rounds;


    public Player()  {
        rounds = 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", userName='" + userName + '\'' +
                ", lastScore=" + lastScore +
                ", highScore=" + highScore +
                ", rounds=" + rounds +
                '}';
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
