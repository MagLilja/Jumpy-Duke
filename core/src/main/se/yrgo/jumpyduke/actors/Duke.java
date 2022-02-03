package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.assets.Assets;

public class Duke extends Actor {
    private static final float GRAVITY = -30f;
    private TextureRegion dukeRegion;
    public static Vector2 dukeStartingPostition;
    private Vector2 dukeVelocity;
    private Vector2 dukeAcceleration;

    public Duke() {
        this.dukeRegion = new TextureRegion(Assets.duke);
        dukeStartingPostition = new Vector2(DukeGame.WIDTH / 2 -
                Assets.duke.getRegionWidth() / 2, DukeGame.HEIGHT / 2);
        setPosition(dukeStartingPostition.x, dukeStartingPostition.y);
        dukeVelocity = new Vector2(0, 0);
        dukeAcceleration = new Vector2(0, GRAVITY);
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
    }

    public Vector2 getDukeStartingPostition() {
        return dukeStartingPostition;
    }
}
