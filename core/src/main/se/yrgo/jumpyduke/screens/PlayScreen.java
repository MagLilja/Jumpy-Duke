package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.actors.CloudLower;
import se.yrgo.jumpyduke.actors.Duke;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class PlayScreen extends ScreenAdapter {
    private DukeGame dukeGame;
    private Duke duke;
    private Stage playStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;

    public PlayScreen() {
        cam = new OrthographicCamera(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT);

        duke = new Duke();
        cloudLower = new CloudLower();

        playStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT));
        playStage.addActor(new Image(Assets.background));
        playStage.addActor(duke);
        playStage.addActor(cloudLower);
    }


    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0, 0, 1);
        playStage.act();
        playStage.draw();
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
