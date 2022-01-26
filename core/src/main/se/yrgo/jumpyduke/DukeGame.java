package se.yrgo.jumpyduke;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.yrgo.jumpyduke.screens.MenuScreen;


public class DukeGame extends Game {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private MenuScreen menuScreen;

    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        setMenuScreen();
    }

    private void setMenuScreen() {
        setScreen(menuScreen);
    }

    @Override
    public void dispose() {

    }
}
