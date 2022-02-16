package se.yrgo.jumpyduke.player;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String userName;
    private int lastScore;
    private int highScore;
    private int rounds;


    public Player() throws IOException {
        rounds = 0;
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
