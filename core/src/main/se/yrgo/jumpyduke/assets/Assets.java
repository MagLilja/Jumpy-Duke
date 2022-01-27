package se.yrgo.jumpyduke.assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static SpriteBatch batch;
    public static TextureAtlas textureAtlas;
    public static TextureRegion background;
    public static TextureRegion duke;/// The duke

    public static void loadAssets(){
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas("pack.atlas");
        background = textureAtlas.findRegion("Background_scale_600x800");
        duke = textureAtlas.findRegion("Duke_scale_76x60.png");

    }

}
