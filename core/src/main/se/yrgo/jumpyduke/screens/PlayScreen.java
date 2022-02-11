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
import se.yrgo.jumpyduke.actors.Pipe;
import se.yrgo.jumpyduke.actors.PipeManager;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PlayScreen extends ScreenAdapter {
    private CloudLower cloudLower2;
    private DukeGame dukeGame;
    private Duke duke;
    private Stage playStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;
    private Pipe bottomPipe;
    private Pipe bottomPipe2;
    private Pipe bottomPipe3;
    private Pipe topPipe;
    private Pipe topPipe2;
    private Pipe topPipe3;
    private PipeManager pipeManager;
    private PipeManager pipeManager2;
    private PipeManager pipeManager3;
    private List<Pipe> listOfPipes;


    public PlayScreen(DukeGame dukeGame) {
        cam = new OrthographicCamera(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT);

        duke = new Duke();
        cloudLower = new CloudLower();
        cloudLower2 = new CloudLower();
        cloudLower2.setPosition(cloudLower2.getWidth(), 0);
        cloudLower2.flip();
        initPipeActors();

        playStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT));
        playStage.addActor(new Image(Assets.background));
        playStage.addActor(duke);
        addPipeActors();

        playStage.addActor(cloudLower2);
        playStage.addActor(cloudLower);
    }
    private void collisionWithPipe() {
        for(Pipe pipe : listOfPipes){

            if (duke.getDukeRectangle().overlaps(pipe.getPipeRectangle())) {
                duke.setDukeState(DukeState.DEAD);
                System.out.println();

            }
        }
    }
    private void initPipeActors() {
        bottomPipe = new Pipe();
        bottomPipe2 = new Pipe();
        bottomPipe3 = new Pipe();
        topPipe = new Pipe();
        topPipe2 = new Pipe();
        topPipe3 = new Pipe();
        listOfPipes = new ArrayList<>();
        Collections.addAll(listOfPipes,bottomPipe,bottomPipe2,bottomPipe3,topPipe,topPipe2,topPipe3);
        pipeManager = new PipeManager(bottomPipe, topPipe);
        pipeManager.initFirstPair();
        pipeManager2 = new PipeManager(bottomPipe2, topPipe2);
        pipeManager2.initSecondPair();
        pipeManager3 = new PipeManager(bottomPipe3, topPipe3);
        pipeManager3.initThirdPair();
    }

    private void addPipeActors() {
        playStage.addActor(pipeManager.getTopPipe());
        playStage.addActor((pipeManager2.getTopPipe()));
        playStage.addActor((pipeManager3.getTopPipe()));
        playStage.addActor(pipeManager.getBottomPipe());
        playStage.addActor(pipeManager2.getBottomPipe());
        playStage.addActor(pipeManager3.getBottomPipe());
    }

    @Override
    public void show() {
        inputlistener();
    }

    private void inputlistener() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    ifAliveJump();
                    System.out.println("Mouse click!");
                }
                return true;

            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    ifAliveJump();
                    System.out.println("Spacebar!");
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


    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0, 0, 1);
        playStage.act();
        playStage.draw();
        pipeManager.reInitialize();
        pipeManager2.reInitialize();
        pipeManager3.reInitialize();
        collisionWithPipe();
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
