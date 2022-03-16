package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.actors.*;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.utils.ScoreDataManager;
import se.yrgo.jumpyduke.player.Player;
import se.yrgo.jumpyduke.sound.SoundManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.BLUE;
import static com.badlogic.gdx.graphics.Color.GREEN;
import static com.badlogic.gdx.graphics.Color.ORANGE;
import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.addAction;

public class PlayScreen extends ScreenAdapter {
    private DukeGame dukeGame;
    private Duke duke;

    private Stage guiStage;
    private Stage playStage;
    private Background backgroundActor;
    private Background backgroundActor2;
    private TextureRegion horizonBackground;

    private OrthographicCamera cam;

    private Cloud cloud;
    private Cloud cloud2;

    private Cloud upperCloud;

    private Label restartLabel;
    private Label playerNameLabel;
    private Label scoreLabel;
    private Label gameModeLabel;

    private float playTime;
    private float deadTime;

    private List<Pipe> listOfPipes;
    private List<PipeDuoManager> listOfPipeDuos;
    private Player player;
    private int score;
    private boolean isCheatMode;

    public PlayScreen(DukeGame dukeGame, Player player, Background backgroundActor, boolean isCheatMode) {
        this.dukeGame = dukeGame;
        this.player = player;
        this.isCheatMode = isCheatMode;
        cam = new OrthographicCamera(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT);
        playStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT, cam));
        guiStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT));
        duke = new Duke();

        score = 0;
        horizonBackground = Assets.horizonBackground;
        Duke.setDukeState(DukeState.ALIVE);

        player.setLastScore(0);
        player.incrementRounds();

        cheatMode();

        Configurations.setGameModeConfigurations();
        initLabels(player);
        initBackgrounds(backgroundActor);
        initClouds();
        initPipes();
        addActors();

    }

    private void cheatMode() {
        if (isCheatMode) {
            duke.setDukeAccelerationToZero();
        }
    }

    @Override
    public void resize(int width, int height) {

        cam.setToOrtho(false, width, height);
        Assets.batch.setProjectionMatrix(cam.combined);
        playStage.getViewport().update(width, height, true);
        guiStage.getViewport().update(width, height, true);

    }

    private void initBackgrounds(Background backgroundActor) {
        this.backgroundActor = backgroundActor;
        double randGap = ThreadLocalRandom.current().nextDouble(Configurations.BACKGROUND_RAND_GAP_LOWERBOUND, Configurations.BACKGROUND_RAND_GAP_UPPERBOUND);
        backgroundActor2 = new Background(randGap);
        backgroundActor2.setPosition((float) (backgroundActor.getWidth() + randGap), 0);
        backgroundActor2.flip();
    }

    private void initLabels(Player player) {
        playerNameLabel = new Label(player.getUserName(), Assets.skin);
        playerNameLabel.setPosition(Configurations.GAME_WIDTH / 2,
                (Configurations.GAME_HEIGHT * 0.90f), Align.center);

        scoreLabel = new Label(String.valueOf(score), Assets.skin);
        scoreLabel.setPosition(Configurations.GAME_WIDTH / 2,
                (Configurations.GAME_HEIGHT * 0.95f), Align.center);

        restartLabel = new Label(Configurations.restartTextString, Assets.skin);
        restartLabel.setPosition(Configurations.GAME_WIDTH / 2,
                Configurations.GAME_HEIGHT / 2 + 150, Align.center);

        gameModeLabel = new Label(MenuScreen.currentGameMode.toString(), Assets.skin);
        gameModeLabel.setPosition(Configurations.GAME_WIDTH * 0.05f,
                Configurations.GAME_HEIGHT * 0.93f);
    }

    private void initPipes() {
        listOfPipes = new ArrayList<>();
        populatelistOfPipes();
        listOfPipeDuos = new ArrayList<>();
        populateListOfPipeDuos();

        listOfPipeDuos.get(0).initFirstPair();
        listOfPipeDuos.get(1).initSecondPair();
        listOfPipeDuos.get(2).initThirdPair();
    }

    private void populateListOfPipeDuos() {
        for (int i = 0; i < 6; i = i + 2) {
            listOfPipeDuos.add(new PipeDuoManager(listOfPipes.get(i),
                    listOfPipes.get(i + 1), new Bug()));
        }
    }

    private void populatelistOfPipes() {
        for (int i = 0; i < 6; ++i) {
            listOfPipes.add(new Pipe());
        }
    }

    private void reInitPipeDuosOnScreen() {
        listOfPipeDuos.forEach(PipeDuoManager::reInitialize);
    }

    private void initClouds() {
        cloud = new Cloud(Assets.cloudLower, 0, 0);
        cloud2 = new Cloud(Assets.cloudLower, 0, 0);
        cloud2.setPosition(cloud2.getWidth(), 0);
        cloud2.flip();

        upperCloud = new Cloud(Assets.cloudUpper, Configurations.GAME_WIDTH, 150);
    }

    private void addPipeActors() {
        for (int i = 0; i < 3; ++i) {
            playStage.addActor(listOfPipeDuos.get(i).getTopPipe());
            playStage.addActor(listOfPipeDuos.get(i).getBottomPipe());
            playStage.addActor(listOfPipeDuos.get(i).getBug());
        }
    }

    private void addActors() {
        Image actor2 = new Image(horizonBackground);
        playStage.addActor(actor2);
        playStage.addActor(backgroundActor);
        playStage.addActor(backgroundActor2);

        playStage.addActor(upperCloud);
        playStage.addActor(duke);
        addPipeActors();
        playStage.addActor(playerNameLabel);
        playStage.addActor(gameModeLabel);
        playStage.addActor(scoreLabel);
        playStage.addActor(cloud2);
        playStage.addActor(cloud);
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
                if (keycode == Input.Keys.SPACE && !isCheatMode) {
                    switch (Duke.getDukeState()) {
                        case ALIVE:
                            ifAliveJump();
                            break;
                        case DEAD:
                            if (playTime >= deadTime + Configurations.RESTART_WAIT_TIME_AFTER_DEAD)
                                dukeGame.setScreen(new MenuScreen(dukeGame, player));
                            break;
                        case IDLE:
                            break;

                    }
                }

                if (keycode == Input.Keys.F8) {
                    if (isCheatMode) {
                        isCheatMode = false;
                        duke.resetDukeAcceleration();
                    } else {
                        isCheatMode = true;
                        duke.setDukeAccelerationToZero();
                    }
                }
                return true;
            }
        });
    }

    private void ifAliveJump() {
        if (Duke.getDukeState() == DukeState.ALIVE) {
            duke.setDukeJump();
            SoundManager.playJumpSound();
            duke.addAction(Actions.moveTo(100,100));
        }
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1);

        if (!isCheatMode) {
            collisionWithPipe();
            collisionWithBug();
            collisionWithClouds();
        }
        restartOptionIfDead();
//        loggingToSystemOut();


        playTime += deltaTime;
        playStage.act();
        playStage.draw();

        guiStage.act();
        guiStage.draw();
        reInitPipeDuosOnScreen();
    }

    private void loggingToSystemOut() {
        System.out.println("" +
                " Play time: " + playTime +
                " Player name: " + player.getUserName() +
                " Rounds: " + player.getRounds() +
                " Score: " + player.getLastScore() +
                " High score: " + player.getHighScore() +
                " State: " + Duke.getDukeState() +
                " Game Mode: " + player.getGameModeState()
                + " Dead time: " + deadTime
        );
    }

    private void collisionWithClouds() {
        if (isBelowClouds()) {
            if (deadTime == 0) {
                deadTime = playTime;
                updateScore();
            }
        }
    }


    public boolean isBelowClouds() {
        if (duke.getDukeCircle().y <= Configurations.LOWER_DEAD_LEVEL) {
            duke.setDukeState(DukeState.DEAD);
            return true;
        }
        return false;
    }

    private void collisionWithPipe() {
        for (Pipe pipe : listOfPipes) {
            if (Intersector.overlaps(duke.getDukeCircle(), pipe.getPipeRectangle())) {
                Duke.setDukeState(DukeState.DEAD);
                rotateDukeWhenDead();


                // to make sure this is only invoked once.
                if (deadTime == 0) {
                    deadTime = playTime;
                    SoundManager.playCollisionSound();
                    updateScore();
                    gameModeLabel.addAction(Actions.moveToAligned(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT / 2 + 0, Align.bottom, .75f, Interpolation.sine));
                    restartLabel.addAction(Actions.color(Color.GREEN));
                    playerNameLabel.addAction(Actions.moveToAligned(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT / 2 + 60, Align.bottom, .75f, Interpolation.sine));
                    scoreLabel.addAction(Actions.moveToAligned(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT / 2 + 30, Align.bottom, .75f, Interpolation.sine));
                    scoreLabel.addAction(Actions.color(Color.GREEN));
                    scoreLabel.addAction(Actions.scaleBy(500, 500));
                    listOfPipes.forEach(p -> p.addAction(Actions.color(ORANGE)));
                }
            }
        }
    }

    private void updateScore() {
        try {
            ScoreDataManager.updateDataFile(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rotateDukeWhenDead() {
        duke.setOrigin(Align.center);
        duke.rotateBy(Configurations.DUKE_DEAD_ROTATION);
    }

    private void collisionWithBug() {
        for (PipeDuoManager pipeDuoManager : listOfPipeDuos) {
            if (Intersector.overlaps(duke.getDukeCircle(), pipeDuoManager.getBug().getBugRectangle())) {
                pipeDuoManager.getBug().moveBy(0, 5000);
                score++;
                updateScoreLabel();
                SoundManager.playBugSound();
                scoreLabel.addAction(Actions.color(Color.GREEN, 1.5f, Interpolation.fade ));

            }
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText(score);
        player.setLastScore(score);
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