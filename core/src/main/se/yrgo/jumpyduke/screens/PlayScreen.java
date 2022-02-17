package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.actors.Bugg;
import se.yrgo.jumpyduke.actors.CloudLower;
import se.yrgo.jumpyduke.actors.Duke;
import se.yrgo.jumpyduke.actors.Pipe;
import se.yrgo.jumpyduke.actors.PipeDuo;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.player.PLayerManager;
import se.yrgo.jumpyduke.player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PlayScreen extends ScreenAdapter {
    private CloudLower cloudLower2;
    private DukeGame dukeGame;
    private Duke duke;

    private Stage guiStage;
    private Stage playStage;

    private OrthographicCamera cam;
    private CloudLower cloudLower;

    private Label restartLabel;
    private Label playerNameLabel;
    private Label scoreLabel;

    private float playTime;
    private float deadTime;

    private List<Pipe> listOfPipes;
    private List<PipeDuo> listOfPipeDuos;
    private Player player;
    private int score;


    public PlayScreen(DukeGame dukeGame, Player player) {
        this.dukeGame = dukeGame;
        this.player = player;
        score = 0;
        player.setLastScore(0);

        cam = new OrthographicCamera(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT);
        duke = new Duke();
        playStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT, cam));
        guiStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT));

        playerNameLabel = new Label(player.getUserName(), Assets.skin);
        playerNameLabel.setPosition(Configurations.GAME_WIDTH / 2, (float) (Configurations.GAME_HEIGHT * 0.90), Align.center);

        scoreLabel = new Label(String.valueOf(score), Assets.skin);
        scoreLabel.setPosition(Configurations.GAME_WIDTH / 2, (float) (Configurations.GAME_HEIGHT * 0.95), Align.center);


        cloudLower = new CloudLower();
        cloudLower2 = new CloudLower();
        cloudLower2.setPosition(cloudLower2.getWidth(), 0);
        cloudLower2.flip();

        listOfPipes = new ArrayList<>();
        listOfPipeDuos = new ArrayList<>();
        initPipes();

        playStage.addActor(new Image(Assets.background));

        playStage.addActor(duke);
        addPipeActors();
        playStage.addActor(playerNameLabel);
        playStage.addActor(scoreLabel);
        playStage.addActor(cloudLower2);
        playStage.addActor(cloudLower);

        restartLabel = new Label(Configurations.RESTART_TEXT_STRING, Assets.skin);
        restartLabel.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT / 2, Align.center);
        player.incrementRounds();


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
                }
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE && Duke.getDukeState() == DukeState.ALIVE) {
                    ifAliveJump();

                }
                if (keycode == Input.Keys.SPACE && Duke.getDukeState() == DukeState.DEAD) {

                    if (playTime >= deadTime + Configurations.RESTART_WAIT_TIME_AFTER_DEAD)
                        dukeGame.setScreen(new MenuScreen(dukeGame, player));
                }
                return true;
            }
        });

    }

    private void ifAliveJump() {
        if (Duke.getDukeState() == DukeState.ALIVE) {
            duke.setDukeJump();
        }
    }


    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0, 0, 1);
        collisionWithPipe();
        collisionWithBugg();
        restartOptionIfDead();
        playTime += deltaTime;
//        System.out.println(playTime);
        playStage.act();
        playStage.draw();

        loggingToSystemOut();


        guiStage.act();
        guiStage.draw();
        reInitPipeDuosOnScreen();
    }

    private void loggingToSystemOut() {
        try {
            System.out.println("" +
                            " Play time: " + playTime +
                            " Player name: " + player.getUserName() +
                            " Rounds: " + player.getRounds() +
                            " Score: " + player.getLastScore() +
                            " High score: " + player.getHighScore() +
                            " State: " + Duke.getDukeState()
                            + " " + PLayerManager.getDataFromJson().get(0).getUserName()
                            + "Dead time: " + deadTime
//                  +  " " + player.isPlayerInList()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPipes() {

        for (int i = 0; i < 6; ++i) {
            listOfPipes.add(new Pipe());

        }
        for (int i = 0; i < 6; i = i + 2) {
            listOfPipeDuos.add(new PipeDuo(listOfPipes.get(i), listOfPipes.get(i + 1), new Bugg()));
        }

        listOfPipeDuos.get(0).initFirstPair();
        listOfPipeDuos.get(1).initSecondPair();
        listOfPipeDuos.get(2).initThirdPair();
    }

    private void addPipeActors() {
        for (int i = 0; i < 3; ++i) {
            playStage.addActor(listOfPipeDuos.get(i).getTopPipe());
            playStage.addActor(listOfPipeDuos.get(i).getBottomPipe());
            playStage.addActor(listOfPipeDuos.get(i).getBugg());
        }

    }

    private void reInitPipeDuosOnScreen() {
        listOfPipeDuos.forEach(PipeDuo::reInitialize);
    }

    private void collisionWithPipe() {
        for (Pipe pipe : listOfPipes) {
            if (duke.getDukeRectangle().overlaps(pipe.getPipeRectangle())) {
                Duke.setDukeState(DukeState.DEAD);
                if (deadTime == 0) {
                    deadTime = playTime;
                    try {
                        PLayerManager.updateDataFile(player);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void collisionWithBugg() {
        for (PipeDuo pipeDuo : listOfPipeDuos) {
            if (duke.getDukeRectangle().overlaps(pipeDuo.getBugg().getBuggRectangle())) {
                pipeDuo.getBugg().moveBy(0, 5000);
                score++;
                scoreLabel.setText(score);
                player.setLastScore(score);
            }
        }
    }


    private void restartOptionIfDead() {
        if (Duke.getDukeState() == DukeState.DEAD) {



            if (playTime >= deadTime + Configurations.RESTART_WAIT_TIME_AFTER_DEAD) {
                playStage.addActor(restartLabel);
            }
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
