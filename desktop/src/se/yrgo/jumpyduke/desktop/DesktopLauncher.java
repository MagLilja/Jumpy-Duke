package se.yrgo.jumpyduke.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.config.Configurations;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = Configurations.GAME_HEIGHT;
        config.width = Configurations.GAME_WIDTH;
        new LwjglApplication(new DukeGame(), config);
    }
}
