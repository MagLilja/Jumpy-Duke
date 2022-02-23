package se.yrgo.jumpyduke;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import se.yrgo.jumpyduke.assets.Assets;

import se.yrgo.jumpyduke.player.Player;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.screens.MenuScreenTextInputListener;

public class DukeGame extends Game {

    private MenuScreen menuScreen;
    private Player player;
    private MenuScreenTextInputListener menuScreenTextInputListener;
    Music music;


    @Override
    public void create() {

        Assets.loadAssets();
        player = new Player();
        menuScreen = new MenuScreen(this, player);
        menuScreenTextInputListener = new MenuScreenTextInputListener(player);



       music();// game music

        if (this.player.getUserName() == null) {
            Gdx.input.getTextInput(menuScreenTextInputListener, "Ange ditt namn", "", "");
        }
        Assets.loadSpriteBatch();
        setScreen(menuScreen);
    }



    public void music() { // game music
        music =  Gdx.audio.newMusic(Gdx.files.absolute("MenuScreenMusic.mp3"));
        music.setLooping(true);
        music.setVolume(0.01f);
        music.getVolume();
        music.play();
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
