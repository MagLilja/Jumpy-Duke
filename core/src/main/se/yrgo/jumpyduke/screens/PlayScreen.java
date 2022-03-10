package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import se.yrgo.jumpyduke.utils.SoundManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class PlayScreen extends ScreenAdapter {
    private DukeGame dukeGame;
    private Duke duke;

    private Stage guiStage;
    private Stage playStage;
    private Background backgroundActor;
    private Background backgroundActor2;


    private OrthographicCamera cam;
    private CloudLower cloudLower;
    private CloudLower cloudLower2;

    private Label restartLabel;
    private Label playerNameLabel;
    private Label scoreLabel;
    private Label gameMode;

    private float playTime;
    private float deadTime;

    private List<Pipe> listOfPipes;
    private List<PipeDuoManager> listOfPipeDuoManagers;
    private Player player;
    private int score;

    public PlayScreen(DukeGame dukeGame, Player player, Background backgroundActor) {
        this.dukeGame = dukeGame;
        this.player = player;
        this.backgroundActor = backgroundActor;
        backgroundActor2 = new Background();
        backgroundActor2.setPosition(backgroundActor.getWidth(),0);
        backgroundActor2.flip();
        score = 0;
        Duke.setDukeState(DukeState.ALIVE);
        player.setLastScore(0);
        player.incrementRounds();

        cam = new OrthographicCamera(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT);
        duke = new Duke();
        playStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT, cam));
        guiStage = new Stage(new StretchViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT));

        Configurations.setGameModeConfigurations();

        initLabels(player);
        initClouds();
        initPipes();
        addActors();

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
                Configurations.GAME_HEIGHT / 2, Align.center);

        gameMode = new Label(MenuScreen.currentGameMode.toString(), Assets.skin);
        gameMode.setPosition(Configurations.GAME_WIDTH * 0.05f,
                Configurations.GAME_HEIGHT * 0.93f);
    }

    private void initPipes() {
        listOfPipes = new ArrayList<>();
        listOfPipeDuoManagers = new ArrayList<>();

        for (int i = 0; i < 6; ++i) {
            listOfPipes.add(new Pipe());
        }

        for (int i = 0; i < 6; i = i + 2) {
            listOfPipeDuoManagers.add(new PipeDuoManager(listOfPipes.get(i),
                    listOfPipes.get(i + 1), new Bug()));
        }

        listOfPipeDuoManagers.get(0).initFirstPair();
        listOfPipeDuoManagers.get(1).initSecondPair();
        listOfPipeDuoManagers.get(2).initThirdPair();
    }

    private void addPipeActors() {
        for (int i = 0; i < 3; ++i) {
            playStage.addActor(listOfPipeDuoManagers.get(i).getTopPipe());
            playStage.addActor(listOfPipeDuoManagers.get(i).getBottomPipe());
            playStage.addActor(listOfPipeDuoManagers.get(i).getBug());
        }
    }

    private void reInitPipeDuosOnScreen() {
        listOfPipeDuoManagers.forEach(PipeDuoManager::reInitialize);
    }

    private void initClouds() {
        cloudLower = new CloudLower();
        cloudLower2 = new CloudLower();
        cloudLower2.setPosition(cloudLower2.getWidth(), 0);
        cloudLower2.flip();
    }

    private void addActors() {
        playStage.addActor(backgroundActor);
        playStage.addActor(backgroundActor2);
        playStage.addActor(duke);
        addPipeActors();
        playStage.addActor(playerNameLabel);
        playStage.addActor(gameMode);
        playStage.addActor(scoreLabel);
        playStage.addActor(cloudLower2);
        playStage.addActor(cloudLower);
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
            SoundManager.playJumpSound();
        }
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1);


        collisionWithPipe();
        collisionWithBug();
        checkIfBelowClouds();
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

    private void checkIfBelowClouds() {
        if (duke.isBelowClouds()) {
            if (deadTime == 0) {
                deadTime = playTime;
                try {
                    ScoreDataManager.updateDataFile(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void collisionWithPipe() {
        for (Pipe pipe : listOfPipes) {
            if (Intersector.overlaps(duke.getDukeCircle(), pipe.getPipeRectangle())) {
                Duke.setDukeState(DukeState.DEAD);
                rotateDukeWhenDead();
                if (deadTime == 0) {
                    deadTime = playTime;
                    SoundManager.playCollisionSound();
                    try {
                        ScoreDataManager.updateDataFile(player);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void rotateDukeWhenDead() {
        duke.setOrigin(Align.center);
        duke.rotateBy(5f);
    }

    private void collisionWithBug() {
        for (PipeDuoManager pipeDuoManager : listOfPipeDuoManagers) {
            if (Intersector.overlaps(duke.getDukeCircle(), pipeDuoManager.getBug().getBugRectangle())) {
                pipeDuoManager.getBug().moveBy(0, 5000);
                incrementAndSetScore();
                SoundManager.playBugSound();
            }
        }
    }

    private void incrementAndSetScore() {
        score++;
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