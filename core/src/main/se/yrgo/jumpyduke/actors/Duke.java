package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class Duke extends Actor {
    private static DukeState dukeState;

    private TextureRegion dukeRegion;

    private Vector2 dukeVelocity;
    private Vector2 dukeAcceleration;

    public Duke() {
        this.dukeRegion = new TextureRegion(Assets.duke);

        setDukeStartingPosition();
        dukeVelocity = new Vector2();
        dukeAcceleration = new Vector2(0, Configurations.DUKE_GRAVITY);
        dukeState = DukeState.ALIVE;
    }

    private void belowClouds() {
        if (getY() <= Configurations.LOWER_DEAD_LEVEL) {
            dukeState = DukeState.DEAD;
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
        super.act(delta);
        dukeVelocity.add(0, dukeAcceleration.y * delta);
        setY(getY() + dukeVelocity.y * delta);
        belowClouds();
    }

    public Vector2 getDukeStartingPostition() {
        return Configurations.dukeStartingPostition;
    }
}
