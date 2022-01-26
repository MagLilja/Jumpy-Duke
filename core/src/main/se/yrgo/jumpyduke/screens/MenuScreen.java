package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.assets.Assets;

public class MenuScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private TextureRegion bg;
    private DukeGame dukeGame;

    public MenuScreen(DukeGame dukeGame) {
        Assets.loadAssets();
        this.batch = Assets.batch;
        this.dukeGame = dukeGame;
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            //To be used
        }
    }

    public void update() {
        //To be used
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(Assets.background, 0, 0);
        batch.end();
        update();
    }

    @Override
    public void dispose() {
//        bg.dispose();
    }
}
