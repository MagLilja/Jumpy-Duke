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
import se.yrgo.jumpyduke.actors.CloudLower;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.config.GameModeManager;
import se.yrgo.jumpyduke.player.ScoreDataManager;
import se.yrgo.jumpyduke.player.Player;

import java.util.Comparator;
import java.util.stream.Collectors;

public class MenuScreen extends ScreenAdapter {
    private CloudLower cloudLower2;
    private DukeGame dukeGame;
    private PlayScreen playScreen;
    private Stage menuStage;
    private Stage guiStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;

    private Label instructionLabel;
    private Label lastScore;
    private Label highScoreLabel;
    private Label showGameMode;
    private Label gameModeOption;


    private Player player;

    public static GameModeManager.GameModeState currentGameMode;


    public MenuScreen(DukeGame dukeGame, Player player) {
        this.dukeGame = dukeGame;
        this.player = player;

        cam = new OrthographicCamera(); //Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT
        if (player.getRounds() == 0) {
            currentGameMode = GameModeManager.GameModeState.NORMAL;
        }


        initStages();
        initLabels(player);
        initClouds();

        addActorsToMenuStage();
        addActorsToGuiStage(player);
    }


    private void initStages() {
        menuStage = new Stage(new ExtendViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT, cam));
        guiStage = new Stage(new ExtendViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT, cam));
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
        showGameMode = new Label("Game Mode\n" + currentGameMode.toString(), Assets.skin);
        showGameMode.setAlignment(Align.center);
        showGameMode.setPosition(Configurations.GAME_WIDTH / 2,Configurations.GAME_HEIGHT * 0.55f, Align.center);

        gameModeOption = new Label("(1) EASY (2) NORMAL (3) HARD ", Assets.skin);
        gameModeOption.setPosition(Configurations.GAME_WIDTH / 2,Configurations.GAME_HEIGHT * 0.45f, Align.center);
    }

    private void initHighScoreLabel() {
        String top3String = ScoreDataManager.getListOfPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getLastScore).reversed())
                .limit(Configurations.SHOW_HIGH_SCORE_LIMIT)
                .map(player -> String.format("%s - %d", player.getUserName(), player.getLastScore()))
                .collect(Collectors.joining("\n"));
        highScoreLabel = new Label(Configurations.HIGH_SCORE_LABEL + "\n -------- \n" + top3String, Assets.skin);
        highScoreLabel.setAlignment(Align.center);
        highScoreLabel.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT * 0.8f, Align.center);
    }

    private void initLastAndHighScoreLabel(Player player) {
        lastScore = new Label(
                "Rounds - " + player.getRounds() +
                        "\n Last score - " + player.getLastScore() +
                        "\n High score - " + player.getHighScore(),
                Assets.skin);
        lastScore.setAlignment(Align.center);
        lastScore.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT * 0.3f, Align.center);
    }

    private void initInstructionLabel() {
        instructionLabel = new Label("Jumpy Duke - Press Space To Play!", Assets.skin);
        instructionLabel.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT * 0.4f, Align.center);
    }

    private void addActorsToMenuStage() {
        menuStage.addActor(new Image(Assets.background));
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

    public void changeGameModeLabel(){
        showGameMode.setText("Game Mode\n" + currentGameMode.toString());
    }

    private void inputlistener(DukeGame dukeGame) {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    playScreen = new PlayScreen(dukeGame, player);
                    MenuScreen.this.dukeGame.setScreen(playScreen);
                    System.out.println("Start!");
                }
                if (keycode == Input.Keys.NUM_1) {
                    currentGameMode = GameModeManager.GameModeState.EASY;
                    changeGameModeLabel();
                }
                if (keycode == Input.Keys.NUM_2) {
                    currentGameMode = GameModeManager.GameModeState.NORMAL;
                    changeGameModeLabel();
                }
                if (keycode == Input.Keys.NUM_3) {
                    currentGameMode = GameModeManager.GameModeState.HARD;
                    changeGameModeLabel();
                }
                return true;
            }
        });

    }


    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0, 0, 1);
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
