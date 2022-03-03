package se.yrgo.jumpyduke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import se.yrgo.jumpyduke.assets.Assets;

import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.player.Player;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.screens.MenuScreenTextInputListener;
import se.yrgo.jumpyduke.sound.SoundManager;

public class DukeGame extends Game {
    private MenuScreen menuScreen;
    private Player player;
    private MenuScreenTextInputListener menuScreenTextInputListener;

    @Override
    public void create() {
        Assets.loadAssets();
        SoundManager.initSounds();
        player = new Player();
        menuScreen = new MenuScreen(this, player);
        menuScreenTextInputListener = new MenuScreenTextInputListener(player);
        SoundManager.playBackgroundMusic();
        if (this.player.getUserName() == null) {
            Gdx.input.getTextInput(menuScreenTextInputListener,
                    Configurations.playerNameInputText, "", "");
        }
        Assets.loadSpriteBatch();
        setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}