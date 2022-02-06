package se.yrgo.jumpyduke;

import com.badlogic.gdx.Game;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.screens.PlayScreen;

public class DukeGame extends Game {

    private PlayScreen menuScreen;

    @Override
    public void create() {
        Assets.loadAssets();
        menuScreen = new PlayScreen();
        Assets.loadSpriteBatch();
        setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
