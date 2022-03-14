package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.config.Configurations;

import java.util.concurrent.ThreadLocalRandom;

import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class Cloud extends Actor {
    private TextureRegion cloudRegion;
    private Vector2 cloudVelocity;

    public Cloud(TextureRegion cloudLower, float x, float y) {
        cloudRegion = new TextureRegion(cloudLower);
        cloudVelocity = new Vector2(Configurations.LOWER_CLOUD_VELOCITY_ALIVE, 0);

        setPosition(x, y);
        setUpperCloudVelocity();
        setWidth(cloudRegion.getRegionWidth());
    }

    private void setUpperCloudVelocity() {
        if (getY() != 0) {
            cloudVelocity.set(-100, 0);
        }

    }

    @Override
    public void act(float delta) {
        setX(getX() + cloudVelocity.x * delta);
        reInitialize();
        if (Duke.getDukeState() == DukeState.DEAD) {
            cloudVelocity.set(Configurations.LOWER_CLOUD_VELOCITY_DEAD, 0);
        }
    }

    public void flip() {
        cloudRegion.flip(true, false);
    }

    private void reInitialize() {
        if (getX() <= -getWidth()) {
            double randGap = ThreadLocalRandom.current().nextDouble(Configurations.UPPERCLOUD_RAND_GAP_LOWERBOUND, Configurations.UPPERCLOUD_RAND_GAP_UPPERBOUND);
            setX(getX() + getWidth() * 2);
            if (getY() != 0) {
                setY((float) randGap);
                logger.info(String.valueOf(getY()));
            }

        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(cloudRegion, getX(), getY());
    }

    public void setCloudXVelocity(float velocity) {
        cloudVelocity.set(velocity, 0);
    }
}
