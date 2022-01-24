package se.yrgo.jumpyduke.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import se.yrgo.jumpyduke.DukeGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = DukeGame.HEIGHT;
        config.width = DukeGame.WIDTH;
        new LwjglApplication(new DukeGame(), config);
    }
}
