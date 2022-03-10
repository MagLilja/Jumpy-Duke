package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

import java.util.concurrent.ThreadLocalRandom;

public class Bug extends Actor {
    private TextureRegion bugTextureRegion;
    private Rectangle bugRectangle;
    private Vector2 bugVelocity;

    public Bug() {
        this.bugTextureRegion = Assets.bug;
        setWidth(bugTextureRegion.getRegionWidth());
        setHeight(bugTextureRegion.getRegionHeight());
        bugVelocity = new Vector2(Configurations.pipe_velocity_alive, 0);
        bugRectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        if (Duke.getDukeState() == DukeState.ALIVE) {
            setX(getX() + bugVelocity.x * delta);
            setY(getY() + bugVelocity.y * delta);
            bugRectangle.setPosition(getX(), getY());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(bugTextureRegion, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    public Rectangle getBugRectangle() {
        return bugRectangle;
    }

    public void setNewRandomXVelocity(){
        double randXVel = ThreadLocalRandom.current().nextDouble(Configurations.BUG_RAND_X_VELOCITY_LOWERBOUND,Configurations.BUG_RAND_X_VELOCITY_UPPERBOUND);
        bugVelocity.set(Configurations.pipe_velocity_alive, (float) randXVel);
    }
}