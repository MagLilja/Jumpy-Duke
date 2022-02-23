package se.yrgo.jumpyduke.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import se.yrgo.jumpyduke.player.ScoreDataManager;

import java.io.IOException;

public class Assets {

    public static TextureAtlas textureAtlas;
    public static TextureRegion background;
    public static TextureRegion duke;/// The duke
    public static TextureRegion cloudLower;
    public static SpriteBatch batch;
    public static TextureRegion pipe;
    public static BitmapFont bitmapFont;
    public static Skin skin;
    public static TextureRegion bugg;
    public static TextureRegion dukeAnimatedOne;
    public static TextureRegion dukeAnimatedTwo;
    public static TextureRegion dukeAnimatedThree;
    public static Animation<TextureRegion> dukeAnimated;
    public static TextureRegion[] dukeAnimatedArray;


    private Assets() {
    }

    public static Animation<TextureRegion> getDukeAnimated() {
        return dukeAnimated;
    }

    public static void loadAssets() {
        textureAtlas = new TextureAtlas("pack.atlas");
        background = textureAtlas.findRegion("Background_scale_600x800");
        duke = textureAtlas.findRegion("Duke_scale_76x60");
        cloudLower = textureAtlas.findRegion("Clouds_lower_600x181");
        pipe = textureAtlas.findRegion("Pipe_bottom_new_140x801");
        bitmapFont = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font_0.png"), false);
        skin = new Skin(Gdx.files.internal("skin/mySkin.json"));
        bugg = textureAtlas.findRegion("Bugg");

        dukeAnimatedOne = textureAtlas.findRegion("DukeOne");
        dukeAnimatedTwo = textureAtlas.findRegion("DukeTwo");
        dukeAnimatedThree = textureAtlas.findRegion("DukeThree");
        dukeAnimatedArray = new TextureRegion[] {dukeAnimatedOne,dukeAnimatedTwo,dukeAnimatedThree};
        dukeAnimated = new Animation(0.20f,dukeAnimatedArray);
        dukeAnimated.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);



        try {
            ScoreDataManager.loadDataFromJson("players.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSpriteBatch() {
        batch = new SpriteBatch();
    }

    public static void dispose() {
        if (batch != null) {
            disposeSpriteBatch();
        }
        disposeTextureAtlas();
    }

    private static void disposeTextureAtlas() {
        textureAtlas.dispose();
    }

    private static void disposeSpriteBatch() {
        batch.dispose();
    }
}
