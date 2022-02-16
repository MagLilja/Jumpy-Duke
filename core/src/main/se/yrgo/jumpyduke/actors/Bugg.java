package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class Bugg extends Actor {
    private TextureRegion buggTextureRegion;
    private Rectangle buggRectangle;

    private Vector2 buggVelocity;

    public Bugg() {
        this.buggTextureRegion = Assets.bugg;
        setWidth(buggTextureRegion.getRegionWidth());
        setHeight(buggTextureRegion.getRegionHeight());
        buggVelocity = new Vector2(Configurations.PIPE_VELOCITY_ALIVE, 0);
    }

    @Override
    public void act(float delta) {
        if (Duke.getDukeState() == DukeState.ALIVE) {
            setX(getX() + buggVelocity.x * delta);
           // buggRectangle.setPosition(getX(), getY());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(buggTextureRegion, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }
}
