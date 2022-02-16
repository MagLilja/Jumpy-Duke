package se.yrgo.jumpyduke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.player.PLayerManager;
import se.yrgo.jumpyduke.player.Player;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.screens.MenuScreenTextInputListener;
import se.yrgo.jumpyduke.screens.PlayScreen;

import java.io.IOException;

public class DukeGame extends Game {

    private MenuScreen menuScreen;
    private Player player;
    private MenuScreenTextInputListener menuScreenTextInputListener;

    @Override
    public void create() {
        Assets.loadAssets();
        player = new Player(); //PLayerManager.getPlayer();//new Player();
        menuScreen = new MenuScreen(this, player);
        menuScreenTextInputListener = new MenuScreenTextInputListener(player);
        if (this.player.getUserName() == null) {
            Gdx.input.getTextInput(menuScreenTextInputListener, "Ange ditt namn", "", "");
        }
        Assets.loadSpriteBatch();
        setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
