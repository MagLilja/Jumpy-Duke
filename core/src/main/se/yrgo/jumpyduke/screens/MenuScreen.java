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
import se.yrgo.jumpyduke.player.Player;

public class MenuScreen extends ScreenAdapter {
    private CloudLower cloudLower2;
    private DukeGame dukeGame;
    private PlayScreen playScreen;
    private Stage menuStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;

    private Label titleLabel;
    private Label lastScore;
    private Label highScore;
    private MenuScreenTextInputListener menuScreenTextInputListener;

    private Player player;


    public MenuScreen(DukeGame dukeGame, Player player) {
        this.dukeGame = dukeGame;
        this.player = player;
        menuScreenTextInputListener = new MenuScreenTextInputListener(this);
        if (this.player.getUserName() == null) {
            Gdx.input.getTextInput(menuScreenTextInputListener, "Ange ditt namn", "", "");
        }
        cam = new OrthographicCamera(); //Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT
        menuStage = new Stage(new ExtendViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT, cam));

        cloudLower = new CloudLower();
        cloudLower2 = new CloudLower();
        cloudLower2.setPosition(cloudLower2.getWidth(), 0);
        cloudLower2.flip();

        titleLabel = new Label("Jumpy Duke - Press Space To Play!", Assets.skin);
        titleLabel.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT / 2, Align.center);

        lastScore = new Label(
                "Rounds - " + player.getRounds() +
                        "\n Last score - " + player.getLastScore() +
                        "\n High score - " + player.getHighScore(),
                Assets.skin);
        lastScore.setAlignment(Align.center);
        lastScore.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT * 0.4f, Align.center);
//
//        highScore = new Label("High score - " + player.getHighScore(), Assets.skin);
//        highScore.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT * 0.35f, Align.center);

        menuStage.addActor(new Image(Assets.background));
        menuStage.addActor(cloudLower2);
        menuStage.addActor(titleLabel);
        if (player.getRounds() > 0) {
            menuStage.addActor(lastScore);
//            menuStage.addActor(highScore);
        }
        menuStage.addActor(cloudLower);
    }

    public void setPlayerName(String name) {
        player.setUserName(name);
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
        menuStage.draw();
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
