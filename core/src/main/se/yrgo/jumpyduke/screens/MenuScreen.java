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
import se.yrgo.jumpyduke.player.PLayerManager;
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
    private Label top3Label;


    private Player player;


    public MenuScreen(DukeGame dukeGame, Player player) {
        this.dukeGame = dukeGame;
        this.player = player;

        cam = new OrthographicCamera(); //Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT

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
        initTop3Label();
    }

    private void initTop3Label() {
        String top3String = PLayerManager.getListOfPLayers().stream()
                .sorted(Comparator.comparingInt(Player::getHighScore).reversed())
                .limit(Configurations.SHOW_HIGH_SCORE_LIMIT)
                .map(player -> String.format("%s - %d", player.getUserName(), player.getHighScore()))
                .collect(Collectors.joining("\n"));
        top3Label = new Label("Top 3 \n -------- \n" + top3String, Assets.skin);
        top3Label.setAlignment(Align.center);
        top3Label.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT * 0.8f, Align.center);

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
        guiStage.addActor(top3Label);
        if (player.getRounds() > 0) {
            guiStage.addActor(lastScore);
        }
    }

    @Override
    public void show() {
        inputlistener(this.dukeGame);
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
