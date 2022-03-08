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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.actors.Background;
import se.yrgo.jumpyduke.actors.CloudLower;
import se.yrgo.jumpyduke.actors.Duke;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.utils.GameModeState;
import se.yrgo.jumpyduke.utils.ScoreDataManager;
import se.yrgo.jumpyduke.player.Player;

import java.util.Comparator;
import java.util.stream.Collectors;

import static se.yrgo.jumpyduke.DukeGame.menuScreenTextInputListener;
import static se.yrgo.jumpyduke.assets.Assets.batch;
import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class MenuScreen extends ScreenAdapter {
    private CloudLower cloudLower2;
    private DukeGame dukeGame;
    private PlayScreen playScreen;
    private Stage menuStage;
    private Stage guiStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;
    private Background backgroundActor;

    private Label instructionLabel;
    private Label lastScore;
    private Label highScoreLabel;
    private Label showGameMode;
    private Label gameModeOption;

    private Player player;

    public static GameModeState currentGameMode;

    public MenuScreen(DukeGame dukeGame, Player player) {
        this.dukeGame = dukeGame;
        this.player = player;
        backgroundActor = new Background();

        Duke.setDukeState(DukeState.DEAD);
        logger.info(Duke.getDukeState().toString());

        cam = new OrthographicCamera();
        if (player.getRounds() == 0) {
            currentGameMode = GameModeState.NORMAL;
        }
        updatePlayerGameModeState();

        backgroundActor.setTexture(currentGameMode);
        initStages();
        initLabels(player);
        initClouds();

        addActorsToMenuStage();
        addActorsToGuiStage(player);
    }

    private void updatePlayerGameModeState() {
        player.setGameModeState(currentGameMode);
        logger.info("Game mode set to: " + currentGameMode);
    }

    private void initStages() {
        menuStage = new Stage(new ExtendViewport(Configurations.GAME_WIDTH,
                Configurations.GAME_HEIGHT, cam));
        guiStage = new Stage(new ExtendViewport(Configurations.GAME_WIDTH,
                Configurations.GAME_HEIGHT, cam));
    }

    private void initClouds() {
        cloudLower = new CloudLower();
        cloudLower2 = new CloudLower();
        cloudLower2.setPosition(cloudLower2.getWidth(), 0);
        cloudLower2.flip();
    }

    private void initLabels(Player player) {

        initInstructionLabel();
        initLastAndHighScoreLabel(player);
        initHighScoreLabel();
        initGameModeLabel();
    }

    private void initGameModeLabel() {
        showGameMode = new Label(Configurations.activeGameModeTitle + "\n"
                + currentGameMode.toString(), Assets.skin);
        showGameMode.setAlignment(Align.center);
        showGameMode.setPosition(Configurations.GAME_WIDTH / 2,
                Configurations.GAME_HEIGHT * 0.55f, Align.center);

        gameModeOption = new Label(Configurations.gameModeText, Assets.skin);
        gameModeOption.setPosition(Configurations.GAME_WIDTH / 2,
                Configurations.GAME_HEIGHT * 0.45f, Align.center);

    }

    private void initHighScoreLabel() {
        highScoreLabel = new Label(Configurations.highScoreLabel + currentGameMode.toString().toLowerCase() + "\n -------- \n"
                + getHighScoreListString(), Assets.skin);
        highScoreLabel.setAlignment(Align.center);
        highScoreLabel.setPosition(Configurations.GAME_WIDTH / 2,
                Configurations.GAME_HEIGHT * 0.8f, Align.center);
    }

    private String getHighScoreListString() {
        return ScoreDataManager.getListOfPlayers().stream()
                .filter(player -> player.getGameModeState() == currentGameMode)
                .sorted(Comparator.comparingInt(Player::getLastScore).reversed())
                .limit(Configurations.SHOW_HIGH_SCORE_LIMIT)
                .map(player -> String.format("%s - %d", player.getUserName(), player.getLastScore()))
                .collect(Collectors.joining("\n"));

    }

    private void initLastAndHighScoreLabel(Player player) {
        lastScore = new Label(
                Configurations.roundText + " - " + player.getRounds() +
                        "\n" + Configurations.lastScoreText + " - " + player.getLastScore() +
                        "\n" + Configurations.highScoreText + " - " + player.getHighScore(),
                Assets.skin);
        lastScore.setAlignment(Align.center);
        lastScore.setPosition(Configurations.GAME_WIDTH / 2,
                Configurations.GAME_HEIGHT * 0.3f, Align.center);
    }

    private void initInstructionLabel() {

        instructionLabel = new Label(Configurations.instructionLabelText, Assets.skin);
        instructionLabel.setAlignment(Align.center);
        instructionLabel.setPosition(Configurations.GAME_WIDTH / 2,
                Configurations.GAME_HEIGHT * 0.4f, Align.center);
    }



    private void addActorsToMenuStage() {
        menuStage.addActor(backgroundActor);
        menuStage.addActor(cloudLower2);
        menuStage.addActor(cloudLower);
    }

    private void addActorsToGuiStage(Player player) {

        guiStage.addActor(instructionLabel);
        guiStage.addActor(highScoreLabel);
        guiStage.addActor(showGameMode);
        guiStage.addActor(gameModeOption);
        if (player.getRounds() > 0) {
            guiStage.addActor(lastScore);
        }
    }

    @Override
    public void show() {
        inputlistener(this.dukeGame);
    }

    public void changeGameModeLabel() {
        showGameMode.setText(Configurations.activeGameModeTitle + "\n"
                + currentGameMode.toString());
    }

    private void changeHighScoreList() {
        highScoreLabel.setText(Configurations.highScoreLabel + currentGameMode.toString().toLowerCase() + "\n -------- \n"
                + getHighScoreListString());
    }

    private void gameModeChanges() {
        changeGameModeLabel();
        changeHighScoreList();
        updatePlayerGameModeState();
        changeGameModeGraphics();
    }

    private void changeGameModeGraphics() {
        backgroundActor.setTexture(currentGameMode);


    }

    private void inputlistener(DukeGame dukeGame) {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    playScreen = new PlayScreen(dukeGame, player,backgroundActor);
                    MenuScreen.this.dukeGame.setScreen(playScreen);
                }
                if (keycode == Input.Keys.NUM_1) {
                    currentGameMode = GameModeState.EASY;
                    gameModeChanges();
                }
                if (keycode == Input.Keys.NUM_2) {
                    currentGameMode = GameModeState.NORMAL;
                    gameModeChanges();
                }
                if (keycode == Input.Keys.NUM_3) {
                    currentGameMode = GameModeState.HARD;
                    gameModeChanges();
                }

                if (keycode == Input.Keys.F2) {
                    Gdx.input.getTextInput(menuScreenTextInputListener,
                            Configurations.playerNameInputText, "", "");
                }

                return true;
            }
        });
    }



    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 0);

        menuStage.act();
        guiStage.act();
        menuStage.draw();
        guiStage.draw();

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