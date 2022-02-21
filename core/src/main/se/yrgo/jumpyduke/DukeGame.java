package se.yrgo.jumpyduke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import org.hibernate.service.spi.ServiceException;
import se.yrgo.jumpyduke.assets.Assets;

import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.config.DatabaseService;
import se.yrgo.jumpyduke.player.Player;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.screens.MenuScreenTextInputListener;

import java.io.IOException;

public class DukeGame extends Game {

    private MenuScreen menuScreen;
    private Player player;
    private MenuScreenTextInputListener menuScreenTextInputListener;

    @Override
    public void create() {

        try {
            DatabaseService.getCurrentSessionFromConfig();
        } catch (ServiceException e) {
            e.printStackTrace();
            Configurations.hasDatabase = false;
        }
        try {
            Assets.loadAssets();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player = new Player();

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
