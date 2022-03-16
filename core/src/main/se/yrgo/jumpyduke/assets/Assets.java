package se.yrgo.jumpyduke.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.utils.ScoreDataManager;

import java.io.IOException;

import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class Assets {
    public static TextureAtlas textureAtlas;
    public static TextureRegion background;
    public static TextureRegion easyBackground;
    public static TextureRegion hardBackground;
    public static TextureRegion duke;
    public static TextureRegion cloudLower;
    public static TextureRegion cloudUpper;
    public static TextureRegion pipe;
    public static TextureRegion bug;
    public static TextureRegion dukeAnimatedOne;
    private static TextureRegion dukeAnimatedTwo;
    private static TextureRegion dukeAnimatedThree;
    private static TextureRegion[] dukeAnimatedArray;

    public static SpriteBatch batch;
    public static Skin skin;
    public static Animation<TextureRegion> dukeAnimated;

    public static Sound bugSound;
    public static Sound collisionSound;
    public static Sound jumpSound;
    public static Music backgroundMusic;

    public static String playersTemplateFile;
    public static String playerScoresFile;

    public static TextureRegion[] getDukeAnimatedArray() {
        return dukeAnimatedArray;
    }

    public static void loadAssets() {
        textureAtlas = new TextureAtlas("pack.atlas");
        background = textureAtlas.findRegion("Background_NORMAL");
        easyBackground = textureAtlas.findRegion("Background_EASY");
        hardBackground = textureAtlas.findRegion("Background_HARD");
        duke = textureAtlas.findRegion("Duke_scale_76x60");
        bug = textureAtlas.findRegion("Bug");
        pipe = textureAtlas.findRegion("Pipe_bottom_new_140x801");
        cloudLower = textureAtlas.findRegion("Clouds_lower_600x181");
        cloudUpper = textureAtlas.findRegion("Clouds_upper-2_601x338");

        skin = new Skin(Gdx.files.internal("skin/mySkin.json"));
        setFontSize();

        dukeAnimatedOne = textureAtlas.findRegion("DukeOne");
        dukeAnimatedTwo = textureAtlas.findRegion("DukeTwo");
        dukeAnimatedThree = textureAtlas.findRegion("DukeThree");
        dukeAnimatedArray = new TextureRegion[]{dukeAnimatedOne, dukeAnimatedTwo, dukeAnimatedThree};
//        dukeAnimated = new Animation(0.20f, dukeAnimatedArray);
//        dukeAnimated.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        bugSound = Gdx.audio.newSound(Gdx.files.internal("sound/bugSound.mp3"));
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("sound/collisionSound.mp3"));
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("sound/jumpySound.mp3"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/backgroundMusic.mp3"));

        playersTemplateFile = "playersTemplate.json";
        playerScoresFile = "playerScores.json";

        try {
            ScoreDataManager.loadDataFromJson(playerScoresFile);
        } catch (IOException e) {
            System.err.println("IOException from player.json");
            e.printStackTrace();
        }
        logger.info("Assets initialized");
    }

    private static void setFontSize() {
        skin.getFont("font").getData().setScale(Configurations.FONT_SIZE);
    }

    public static Animation<TextureRegion> getDukeAnimated() {
        return dukeAnimated;
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