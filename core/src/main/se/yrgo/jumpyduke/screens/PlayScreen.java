package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.actors.CloudLower;
import se.yrgo.jumpyduke.actors.Duke;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class PlayScreen extends ScreenAdapter {
    private CloudLower cloudLower2;
    private DukeGame dukeGame;
    private Duke duke;
    private Stage playStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;

    private void inputlistener() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    ifAliveJump();
                }
                return true;

            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    ifAliveJump();
                }
                return true;
            }
        });

    }

    private void ifAliveJump() {
        if (duke.getDukeState() == DukeState.ALIVE) {
            duke.setDukeJump();
        }
        System.out.println(duke.getDukeState());
    }

    public PlayScreen() {
        cam = new OrthographicCamera(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT);

        duke = new Duke();
        cloudLower = new CloudLower();
        cloudLower2 = new CloudLower();
        cloudLower2.setPosition(cloudLower2.getWidth(),0);
        cloudLower2.flip();


        playStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT));
        playStage.addActor(new Image(Assets.background));
        playStage.addActor(duke);
        playStage.addActor(cloudLower2);
        playStage.addActor(cloudLower);
        inputlistener();
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
