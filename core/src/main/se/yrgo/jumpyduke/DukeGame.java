package se.yrgo.jumpyduke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.player.Player;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.screens.MenuScreenTextInputListener;
import se.yrgo.jumpyduke.screens.PlayScreen;

import java.io.IOException;

public class DukeGame extends Game {

    private MenuScreen menuScreen;
    private Player player;

    @Override
    public void create() {
        Assets.loadAssets();
        player = new Player();
        menuScreen = new MenuScreen(this, player);
        Assets.loadSpriteBatch();
        setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
