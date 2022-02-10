package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class Pipe extends Actor {
    private TextureRegion pipeRegion;
    private Vector2 pipeVelocity;


    public Pipe() {
        pipeRegion = new TextureRegion(Assets.pipe);
        setWidth(pipeRegion.getRegionWidth() * 0.65f);
        setHeight(pipeRegion.getRegionHeight() * 0.65f);
        pipeVelocity = new Vector2(Configurations.PIPE_VELOCITY, 0);
        setOrigin(Align.center);
    }

    @Override
    public void act(float delta) {
        setX(getX() + pipeVelocity.x * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pipeRegion, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    public TextureRegion getPipeRegion() {
        return pipeRegion;
    }
}
