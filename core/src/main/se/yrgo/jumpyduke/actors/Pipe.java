package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

import static se.yrgo.jumpyduke.assets.Assets.batch;

public class Pipe extends Actor {
    private TextureRegion pipeRegion;
    private Vector2 pipeVelocity;
    private Rectangle pipeRectangle;
    private boolean isTopPipe = false;

    public Rectangle getPipeRectangle() {
        return pipeRectangle;
    }

    public Pipe() {
        pipeRegion = new TextureRegion(Assets.pipe);
        setWidth(pipeRegion.getRegionWidth());
        setHeight(pipeRegion.getRegionHeight());
        pipeRectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        pipeVelocity = new Vector2(Configurations.pipe_velocity_alive, 0);
        setOrigin(Align.center);
    }

    public void setTopPipe(boolean isTopPipe) {
        this.isTopPipe = isTopPipe;
        
    }

    @Override
    public void act(float delta) {
        if (Duke.getDukeState() == DukeState.ALIVE) {
            setX(getX() + pipeVelocity.x * delta);
            pipeRectangle.setPosition(getX(), getY());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(pipeRegion, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
//
//
//        604, 220
//        size: 140, 801

//        boolean flipX = false;
//        boolean flipy = false;
//        if (isTopPipe) {
//            flipX = true;
//            flipy = true;
//        }
//        batch.draw(pipeRegion.getTexture(), getX(), getY(), getOriginX(), getOriginY(),
//                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 604, 220, 140, 801, isTopPipe, isTopPipe);
    }

    public TextureRegion getPipeRegion() {
        return pipeRegion;
    }
}
