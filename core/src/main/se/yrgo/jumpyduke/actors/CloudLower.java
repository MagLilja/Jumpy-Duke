package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class CloudLower extends Actor {
    private TextureRegion cloudRegion;
    private Vector2 lowerCloudVelocity;

    @Override
    public void act(float delta) {
        setX(getX() + lowerCloudVelocity.x * delta);
        reInitialize();
    }

    public CloudLower() {
        cloudRegion = new TextureRegion(Assets.cloudLower);
        lowerCloudVelocity = new Vector2(Configurations.LOWER_CLOUD_VELOCITY, 0);
        setPosition(Configurations.cloudLowerPosition.x, Configurations.cloudLowerPosition.y);
        setWidth(cloudRegion.getRegionWidth());
    }

    public void flip() {
        cloudRegion.flip(true, false);
    }

    private void reInitialize() {
        if (getX() <= -getWidth()) {
            setX(getX() + getWidth() * 2);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(cloudRegion, getX(), getY());
    }

}
