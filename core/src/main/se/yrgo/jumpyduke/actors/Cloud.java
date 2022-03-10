package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

import java.util.concurrent.ThreadLocalRandom;

public class Cloud extends Actor {
    private TextureRegion cloudRegion;
    private Vector2 lowerCloudVelocity;

    public Cloud(TextureRegion cloudLower, float x, float y) {
        cloudRegion = new TextureRegion(cloudLower);
        lowerCloudVelocity = new Vector2(Configurations.LOWER_CLOUD_VELOCITY_ALIVE, 0);

        setPosition(x, y);
        if (getY() != 0){
            lowerCloudVelocity.set(-100, 0);
        }
        setWidth(cloudRegion.getRegionWidth());
    }

    @Override
    public void act(float delta) {
        setX(getX() + lowerCloudVelocity.x * delta);
        reInitialize();
        if (Duke.getDukeState() == DukeState.DEAD) {
            lowerCloudVelocity.set(Configurations.LOWER_CLOUD_VELOCITY_DEAD, 0);
        }
    }

    public void flip() {
        cloudRegion.flip(true, false);
    }

    private void reInitialize() {
        if (getX() <= -getWidth()) {
            double randGap = ThreadLocalRandom.current().nextDouble(Configurations.UPPERCLOUD_RAND_GAP_LOWERBOUND,Configurations.UPPERCLOUD_RAND_GAP_UPPERBOUND);
            setX(getX() + getWidth() * 2);
            if (getY() != 0){
                setY((float) randGap);
            }

        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(cloudRegion, getX(), getY());
    }
}
