package se.yrgo.jumpyduke.screens;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.assets.Assets;

public class MenuScreen extends ScreenAdapter {
    private DukeGame dukeGame;
    private Vector2 dukeStartingPostition;

    public MenuScreen() {
        dukeStartingPostition = new Vector2(DukeGame.WIDTH / 2 - Assets.duke.getRegionWidth() / 2,DukeGame.HEIGHT / 2);
    }

    public Vector2 getDukeStartingPostition() {
        return dukeStartingPostition;
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0, 0, 1);
        Assets.batch.begin();
        Assets.batch.draw(Assets.background, 0, 0);
        Assets.batch.draw(Assets.duke, dukeStartingPostition.x, dukeStartingPostition.y);
        Assets.batch.draw(Assets.cloudLower, 0, 0);
        Assets.batch.end();
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
