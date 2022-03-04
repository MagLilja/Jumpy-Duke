package se.yrgo.jumpyduke.utils;

import com.badlogic.gdx.utils.Logger;

public class GameUtils {
    public static Logger logger = new Logger("GDX logger", getLoggerLevel());

    private static int getLoggerLevel(){
        return Logger.INFO;
    }

}
