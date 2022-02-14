package se.yrgo.jumpyduke.config;

import com.badlogic.gdx.math.Vector2;
import se.yrgo.jumpyduke.assets.Assets;

public class Configurations {

    public static final float DUKE_GRAVITY = -900f;
    public static final float DUKE_JUMP_VELOCITY = 300f;
    public static final float LOWER_CLOUD_VELOCITY_ALIVE = -120f;
    public static final float LOWER_CLOUD_VELOCITY_DEAD = -20f;
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 800;
    public static final int LOWER_DEAD_LEVEL = 100;
    public static final int LOWER_CLOUD_WIDTH = 600;
    public static final float PIPE_VELOCITY_ALIVE = -100f;
    public static final int GAP_SIZE = 200;
    public static final double PIPE_GAP_TOP_LEVEL = GAME_HEIGHT * 0.75f;
    public static final double PIPE_GAP_BOTTOM_LEVEL = GAME_HEIGHT * 0.25f;
    public static final int PIPE_SPACING = 350;




    public static Vector2 dukeStartingPostition = new Vector2(
            GAME_WIDTH / 2 - Assets.duke.getRegionWidth() / 2,
            GAME_HEIGHT / 2);

    public static Vector2 cloudLowerPosition = new Vector2(0, 0);
}
