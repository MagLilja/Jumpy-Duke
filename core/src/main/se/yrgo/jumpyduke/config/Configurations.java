package se.yrgo.jumpyduke.config;

import com.badlogic.gdx.math.Vector2;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.screens.MenuScreen;

public class Configurations {
    /**
     * ############## Screen text labels and strings ##############
     */
    public static final String RESTART_TEXT_STRING = "Press space To Play Again!";
    public static final CharSequence HIGH_SCORE_LABEL = "High Score";
    public static String playersTemplateFile = "playersTemplate.json";
    public static CharSequence gameModeText = "(1) EASY (2) NORMAL (3) HARD";
    public static CharSequence activeGameModeTitle = "Game Mode";
    public static String roundText = "Rounds";
    public static String lastScoreText = "Last score";
    public static String highScoreText = "High score";
    public static String instructionLabelText = "Jumpy Duke - Press Space To Play!";
    public static String playerNameInputText = "Enter your name";

    /**
     * ############## Numerical constants for configuration ##############
     */
    public static final float DUKE_GRAVITY = -900f;
    public static final float DUKE_JUMP_VELOCITY = 300f;
    public static final float LOWER_CLOUD_VELOCITY_ALIVE = -120f;
    public static final float LOWER_CLOUD_VELOCITY_DEAD = -20f;
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 800;
    public static final int LOWER_DEAD_LEVEL = 100;
    public static final double PIPE_GAP_TOP_LEVEL = GAME_HEIGHT * 0.75f;
    public static final double PIPE_GAP_BOTTOM_LEVEL = GAME_HEIGHT * 0.25f;
    public static final double BUG_TOP_LEVEL = GAME_HEIGHT * 0.65f;
    public static final double BUG_BOTTOM_LEVEL = GAME_HEIGHT * 0.35f;
    public static final float RESTART_WAIT_TIME_AFTER_DEAD = 3;
    public static final long SHOW_HIGH_SCORE_LIMIT = 5;
    public static final long LIMIT_OUTPUT_LIST_TO = 100;

    public static float pipe_velocity_alive;
    public static int gap_size;
    public static int pipe_spacing;

    public static void setGameModeConfigurations() {
        switch (MenuScreen.currentGameMode) {
            case EASY:
                pipe_velocity_alive = -100f;
                gap_size = 250;
                pipe_spacing = 400;
                break;
            case NORMAL:
                pipe_velocity_alive = -125f;
                gap_size = 200;
                pipe_spacing = 350;
                break;
            case HARD:
                pipe_velocity_alive = -150f;
                gap_size = 175;
                pipe_spacing = 325;
                break;
        }
    }

    public static Vector2 cloudLowerPosition = new Vector2(0, 0);

    public static Vector2 dukeStartingPostition = new Vector2(
            GAME_WIDTH / 2 - Assets.duke.getRegionWidth() / 2,
            GAME_HEIGHT / 2);
}
