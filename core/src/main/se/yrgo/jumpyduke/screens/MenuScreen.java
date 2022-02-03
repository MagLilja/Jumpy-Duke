package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.actors.CloudLower;
import se.yrgo.jumpyduke.actors.Duke;
import se.yrgo.jumpyduke.assets.Assets;

public class MenuScreen extends ScreenAdapter {
    private DukeGame dukeGame;
    private Duke duke;
    private Stage menuStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;

    public MenuScreen() {
        cam = new OrthographicCamera(DukeGame.WIDTH, DukeGame.HEIGHT);
        duke = new Duke();
        cloudLower = new CloudLower();
        menuStage = new Stage(new StretchViewport(DukeGame.WIDTH, DukeGame.HEIGHT));
        menuStage.addActor(duke);
        menuStage.addActor(cloudLower);
    }


    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0, 0, 1);
        Assets.batch.begin();
        Assets.batch.draw(Assets.background, 0, 0);
        Assets.batch.draw(Assets.cloudLower, 0, 0);
        Assets.batch.end();
        menuStage.act();
        menuStage.draw();
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
