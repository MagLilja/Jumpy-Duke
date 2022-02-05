package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class CloudLower extends Actor {
    private TextureRegion cloudRegion;

    public CloudLower() {
        cloudRegion = new TextureRegion(Assets.cloudLower);
        setPosition(Configurations.cloudLowerPosition.x,Configurations.cloudLowerPosition.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(cloudRegion, getX(), getY());
    }

}
