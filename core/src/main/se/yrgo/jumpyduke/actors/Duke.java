package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class Duke extends Actor {
    private static DukeState dukeState;// = DukeState.ALIVE;

    private TextureRegion dukeRegion;
    private Rectangle dukeRectangle;

    private Vector2 dukeVelocity;
    private Vector2 dukeAcceleration;
    private float time;

    public Duke() {
        this.dukeRegion = new TextureRegion(Assets.duke);

        dukeState = DukeState.ALIVE;
        setWidth(dukeRegion.getRegionWidth());
        setHeight(dukeRegion.getRegionHeight());
        setDukeStartingPosition();
        dukeRectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        dukeVelocity = new Vector2();
        dukeAcceleration = new Vector2(0, Configurations.DUKE_GRAVITY);
    }

    public Rectangle getDukeRectangle() {
        return dukeRectangle;
    }

    private void belowClouds() {
        if (getY() <= Configurations.LOWER_DEAD_LEVEL) {
            setDukeState(DukeState.DEAD);
        }
    }


    public void setDukeJump() {
        this.dukeVelocity.y = Configurations.DUKE_JUMP_VELOCITY;
    }


    public static DukeState getDukeState() {
        return dukeState;
    }

    private void setDukeStartingPosition() {
        setPosition(Configurations.dukeStartingPostition.x, Configurations.dukeStartingPostition.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(dukeRegion, getX(), getY());
    }

    @Override
    public void act(float delta) {
        time += delta;

        dukeRegion = Assets.dukeAnimated.getKeyFrame(time);

        System.out.println(Assets.dukeAnimated.getKeyFrame(time));
        dukeVelocity.add(0, dukeAcceleration.y * delta);
        setY(getY() + dukeVelocity.y * delta);
        belowClouds();
        dukeRectangle.setPosition(getX(), getY());


    }

    public static void setDukeState(DukeState dukeState) {
        Duke.dukeState = dukeState;
    }

    public Vector2 getDukeStartingPostition() {
        return Configurations.dukeStartingPostition;
    }

}
