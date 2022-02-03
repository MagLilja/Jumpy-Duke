package se.yrgo.jumpyduke.assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureAtlas textureAtlas;
    public static TextureRegion background;
    public static TextureRegion duke;/// The duke
    public static TextureRegion cloudLower;
    public static SpriteBatch batch;


    public static void loadAssets() {
        textureAtlas = new TextureAtlas("pack.atlas");
        background = textureAtlas.findRegion("Background_scale_600x800");
        duke = textureAtlas.findRegion("Duke_scale_76x60");
        cloudLower = textureAtlas.findRegion("Clouds_lower_600x181");
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
