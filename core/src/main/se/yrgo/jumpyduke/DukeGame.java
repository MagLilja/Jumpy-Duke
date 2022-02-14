package se.yrgo.jumpyduke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.screens.MenuScreenTextInputListener;
import se.yrgo.jumpyduke.screens.PlayScreen;

public class DukeGame extends Game {

    private MenuScreen menuScreen;
    private MenuScreenTextInputListener menuScreenTextInputListener;

    @Override
    public void create() {
        Assets.loadAssets();
//        Gdx.input.getTextInput(menuScreenTextInputListener, "Ange ditt namn", "", "");
        menuScreen = new MenuScreen(this);
        Assets.loadSpriteBatch();
        setScreen(menuScreen);
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
