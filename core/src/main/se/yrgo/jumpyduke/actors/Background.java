package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.DukeState;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.utils.GameModeState;

import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class Background extends Actor {
    private TextureRegion backgroundRegion;
    private Vector2 backgroundVelocity;

    public Background() {
        backgroundRegion = new TextureRegion(Assets.background);
        backgroundVelocity = new Vector2(0, 0);
        setPosition(0,0);
        setWidth(backgroundRegion.getRegionWidth());
    }

    @Override
    public void act(float delta) {
        if (Duke.getDukeState() == DukeState.ALIVE) {
            backgroundVelocity.set(Configurations.BACKGROUND_VELOCITY ,0);
        } else if (Duke.getDukeState() == DukeState.DEAD){
            backgroundVelocity.set(-10,0);
        }
        else {
            backgroundVelocity.set(0,0);
        }
        setX(getX() + backgroundVelocity.x * Gdx.graphics.getDeltaTime());
        reInitialize();
    }

    public void flip() {
        backgroundRegion.flip(true, false);
    }

    private void reInitialize() {
        if (getX() <= -getWidth()) {
            setX(getX() + getWidth() * 2);
        }
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
       // batch.isBlendingEnabled();
        //

        if (MenuScreen.currentGameMode == GameModeState.HARD)
        batch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_BLEND_SRC_RGB);
            // batch.setColor(255,255,255,0.88f);

          batch.draw(backgroundRegion, getX(), getY());
    }

    public TextureRegion getBackgroundRegion() {
        return backgroundRegion;
    }

    public void setTexture(GameModeState currentGameMode) {
        switch (currentGameMode){
            case HARD:
                backgroundRegion.setRegion(Assets.hardBackground);
                break;
            case EASY:
               backgroundRegion.setRegion(Assets.easyBackground);
                break;
            case NORMAL:
                backgroundRegion.setRegion(Assets.background);
                break;

        }

    }
}







