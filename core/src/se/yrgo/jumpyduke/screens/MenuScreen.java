package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.jumpyduke.DukeGame;

public class MenuScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private Texture bg;
    private DukeGame dukeGame;

    public MenuScreen(SpriteBatch batch, DukeGame dukeGame) {
        this.batch = batch;
        this.dukeGame = dukeGame;
        bg = new Texture("Background_scale_600x800.png");
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
        batch.draw(bg, 0, 0);
        batch.end();
        update();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
