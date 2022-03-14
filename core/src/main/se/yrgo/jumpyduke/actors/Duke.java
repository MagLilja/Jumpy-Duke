package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class Duke extends Actor {
    public static Vector2 dukeStartingPostition;
    private static DukeState dukeState;
    private TextureRegion dukeRegion = new TextureRegion(Assets.dukeAnimatedOne);
    private Circle dukeCircle;
    private Vector2 dukeVelocity;
    private Vector2 dukeAcceleration;
    private float playTime;
    private ShapeRenderer debugCircle;

    public Duke() {
        dukeState = DukeState.ALIVE;
        setWidth(dukeRegion.getRegionWidth());
        setHeight(dukeRegion.getRegionHeight());
        setDukeStartingPosition();
        dukeCircle = new Circle(getX(), getY(), getWidth() / 3.5f);
        dukeVelocity = new Vector2();
        dukeAcceleration = new Vector2(0, Configurations.DUKE_GRAVITY);
        debugCircle = new ShapeRenderer();

    }

    public Circle getDukeCircle() {
        return dukeCircle;
    }

    public boolean isBelowClouds() {
        if (getY() <= Configurations.LOWER_DEAD_LEVEL) {
            setDukeState(DukeState.DEAD);
            return true;
        }
        return false;
    }

    public void setDukeJump() {
        this.dukeVelocity.y = Configurations.DUKE_JUMP_VELOCITY;
    }

    public static DukeState getDukeState() {
        return dukeState;
    }

    private void setDukeStartingPosition() {
        dukeStartingPostition = new Vector2(
                Configurations.GAME_WIDTH / 2.0f - Assets.duke.getRegionWidth() / 2.0f,
                Configurations.GAME_HEIGHT / 2.0f);
        setPosition(dukeStartingPostition.x, dukeStartingPostition.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(dukeRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        playTime += delta;
        dukeRegion = Assets.getDukeAnimated()
                .getKeyFrame(playTime, true);
        dukeVelocity.add(0, dukeAcceleration.y * delta);
        setY(getY() + dukeVelocity.y * delta);
        isBelowClouds();
        dukeCircle.setPosition(getX() + getWidth() / 1.5f, getY() + getHeight() / 2);
    }

    public static void setDukeState(DukeState dukeState) {
        Duke.dukeState = dukeState;
    }

    // This function is kept for future debugging
    public void debugCircle() {
        debugCircle.begin(ShapeRenderer.ShapeType.Line);
        debugCircle.setColor(Color.RED);
        debugCircle.circle(dukeCircle.x, dukeCircle.y, dukeCircle.radius);
        debugCircle.setAutoShapeType(true);
        debugCircle.end();
    }

    public void setDukeAccelerationToZero() {
        dukeAcceleration.set(0, 0);
    }

    public void resetDukeAcceleration() {
        dukeAcceleration.set(0, Configurations.DUKE_GRAVITY);
    }

}
