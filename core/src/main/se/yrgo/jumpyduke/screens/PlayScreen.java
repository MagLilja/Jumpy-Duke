package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.actors.CloudLower;
import se.yrgo.jumpyduke.actors.Duke;
import se.yrgo.jumpyduke.actors.Pipe;
import se.yrgo.jumpyduke.actors.PipeDuo;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

import java.util.ArrayList;
import java.util.List;


public class PlayScreen extends ScreenAdapter {
    private CloudLower cloudLower2;
    private DukeGame dukeGame;
    private Duke duke;
    private Stage playStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;

    private List<Pipe> listOfPipes;
    private Label restartLabel;
    private float playTime;
    private List<PipeDuo> listOfPipeDuos;


    public PlayScreen(DukeGame dukeGame) {
        this.dukeGame = dukeGame;
        cam = new OrthographicCamera(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT);
        duke = new Duke();

        cloudLower = new CloudLower();
        cloudLower2 = new CloudLower();
        cloudLower2.setPosition(cloudLower2.getWidth(), 0);
        cloudLower2.flip();

        listOfPipes = new ArrayList<>();
        listOfPipeDuos = new ArrayList<>();
        initPipeActors();

        playStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT));
        playStage.addActor(new Image(Assets.background));
        playStage.addActor(duke);
        addPipeActors();
        playStage.addActor(cloudLower2);
        playStage.addActor(cloudLower);

        restartLabel = new Label("Press F2 To Play Again!", new Label.LabelStyle(Assets.bitmapFont, Color.WHITE));
        restartLabel.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT / 2, Align.center);

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
                if (keycode == Input.Keys.F2 && duke.getDukeState() == DukeState.DEAD) {
                    dukeGame.setScreen(new PlayScreen(dukeGame));
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
        collisionWithPipe();
        restartOptionIfDead();
        playTime += deltaTime;
        System.out.println(playTime);
        playStage.act();
        playStage.draw();
        reInitPipeDuosOnScreen();
    }

    private void initPipeActors() {

        for (int i = 0; i < 4; ++i) {
            listOfPipes.add(new Pipe());
            listOfPipes.add(new Pipe());
        }
        for (int i = 0; i < 6; i = i + 2) {
            listOfPipeDuos.add(new PipeDuo(listOfPipes.get(i), listOfPipes.get(i + 1)));
        }

        listOfPipeDuos.get(0).initFirstPair();
        listOfPipeDuos.get(1).initSecondPair();
        listOfPipeDuos.get(2).initThirdPair();
    }

    private void addPipeActors() {
        for (int i = 0; i < 3; ++i) {
            playStage.addActor(listOfPipeDuos.get(i).getTopPipe());
            playStage.addActor(listOfPipeDuos.get(i).getBottomPipe());
        }

    }

    private void reInitPipeDuosOnScreen() {
        listOfPipeDuos.stream().forEach(pipeDuo -> pipeDuo.reInitialize());
    }
    private void collisionWithPipe() {
        for (Pipe pipe : listOfPipes) {

            if (duke.getDukeRectangle().overlaps(pipe.getPipeRectangle())) {
                duke.setDukeState(DukeState.DEAD);
                System.out.println();

            }
        }
    }


    private void restartOptionIfDead() {
        if (duke.getDukeState() == DukeState.DEAD) {
            playStage.addActor(restartLabel);
        }
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
