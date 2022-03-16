package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.utils.GameModeState;

import java.util.concurrent.ThreadLocalRandom;

import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class Background extends Actor {
    private final TextureRegion backgroundRegion;
    private final Vector2 backgroundVelocity;
    private static double initialRandGap;

    public Background() {
        backgroundRegion = new TextureRegion(Assets.background);
        backgroundVelocity = new Vector2(0, 0);
        setPosition(0, 0);
        setWidth(backgroundRegion.getRegionWidth());

    }

    public Background(double randGap) {
        this();
        initialRandGap = randGap;
    }

    @Override
    public void act(float delta) {
        if (Duke.getDukeState() == DukeState.ALIVE) {
            backgroundVelocity.set(Configurations.BACKGROUND_VELOCITY, 0);
        } else if (Duke.getDukeState() == DukeState.DEAD) {
            backgroundVelocity.set(0, 0);
        } else {
            backgroundVelocity.set(0, 0);
        }
        setX(getX() + backgroundVelocity.x * Gdx.graphics.getDeltaTime());
        reInitialize();
    }

    public void flip() {
        backgroundRegion.flip(true, false);
    }

    private void reInitialize() {
        if (getX() <= -getWidth()) {
            double randGap = ThreadLocalRandom.current().nextDouble(Configurations.BACKGROUND_RAND_GAP_LOWERBOUND, Configurations.BACKGROUND_RAND_GAP_UPPERBOUND);
            setX((float) ((getX() + getWidth() * 2) + randGap + initialRandGap));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (MenuScreen.currentGameMode == GameModeState.HARD) {
            batch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_BLEND_SRC_RGB);
        }
        batch.draw(backgroundRegion, getX(), getY());
    }

    public void setTexture(GameModeState currentGameMode) {
        switch (currentGameMode) {
            case HARD:
                backgroundRegion.setRegion(Assets.hardBackground);
                break;
            case EASY:
                backgroundRegion.setRegion(Assets.easyBackground);
                break;
            case NORMAL:
                backgroundRegion.setRegion(Assets.background);
                break;

        }

    }
}







