package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.actors.CloudLower;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.player.Player;

public class MenuScreen extends ScreenAdapter {
    private CloudLower cloudLower2;
    private DukeGame dukeGame;
    private PlayScreen playScreen;
    //    private Duke duke;
    private Stage menuStage;
    private OrthographicCamera cam;
    private CloudLower cloudLower;

    private Label titleLabel;
    private MenuScreenTextInputListener menuScreenTextInputListener;




    public MenuScreen(DukeGame dukeGame) {
        this.dukeGame = dukeGame;
        menuScreenTextInputListener = new MenuScreenTextInputListener();
        Gdx.input.getTextInput(menuScreenTextInputListener, "Ange ditt namn", "", "");
        playScreen = new PlayScreen(this.dukeGame);
        cam = new OrthographicCamera(); //Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT
        menuStage = new Stage(new ExtendViewport(Configurations.GAME_WIDTH, Configurations.GAME_HEIGHT, cam));




        cloudLower = new CloudLower();
        cloudLower2 = new CloudLower();
        cloudLower2.setPosition(cloudLower2.getWidth(), 0);
        cloudLower2.flip();


//        skin.get("font-label", BitmapFont.class).getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        titleLabel = new Label("Jumpy Duke - Press Space To Play!", Assets.skin);
        titleLabel.setPosition(Configurations.GAME_WIDTH / 2, Configurations.GAME_HEIGHT / 2, Align.center);
//
//        playerLabel = new Label("Jumpy Duke - Press Space To Play!", new Label.LabelStyle(Assets.bitmapFont, Color.WHITE));
//        playerLabel.setPosition(Configurations.GAME_WIDTH/2,Configurations.GAME_HEIGHT/2, Align.center);
//
//        name = new Label("NAME : ", skin);
//        nameField =new TextField("", skin);

//        Table table=new Table();
//        table.defaults().pad(10);
//        table.setPosition(300,400);
//        table.row();
//        table.add(name).left();
//        table.add(nameField).width(300);

        menuStage.addActor(new Image(Assets.background));
        menuStage.addActor(cloudLower2);
        menuStage.addActor(titleLabel);
        menuStage.addActor(cloudLower);

//        menuStage.addActor(table);
    }

    @Override
    public void show() {
        inputlistener();
    }

    private void inputlistener() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    dukeGame.setScreen(playScreen);
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
