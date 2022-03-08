package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.screens.MenuScreen;
import se.yrgo.jumpyduke.utils.GameModeState;

import java.awt.*;

import static se.yrgo.jumpyduke.assets.Assets.batch;
import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class Background extends Actor {
    private TextureRegion backgroundRegion;
//    private Vector2 lowerCloudVelocity;

    public Background() {
        backgroundRegion = new TextureRegion(Assets.background);
//        lowerCloudVelocity = new Vector2(Configurations.LOWER_CLOUD_VELOCITY_ALIVE, 0);
//        setPosition(Configurations.cloudLowerPosition.x, Configurations.cloudLowerPosition.y);
//        setWidth(cloudRegion.getRegionWidth());
    }

/*    @Override
    public void act(float delta) {
        setX(getX() + lowerCloudVelocity.x * delta);
        reInitialize();
        if (Duke.getDukeState() == DukeState.DEAD) {
            lowerCloudVelocity.set(Configurations.LOWER_CLOUD_VELOCITY_DEAD, 0);
        }
    }

    public void flip() {
        cloudRegion.flip(true, false);
    }

    private void reInitialize() {
        if (getX() <= -getWidth()) {
            setX(getX() + getWidth() * 2);
        }
    }

 */

    @Override
    public void draw(Batch batch, float parentAlpha) {
       // batch.isBlendingEnabled();
        //
        //if (MenuScreen.currentGameMode == GameModeState.HARD)
         //batch.setBlendFunction(GL20.GL_DST_ALPHA, GL20.GL_BLEND_SRC_RGB);
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
            default:
                backgroundRegion.setRegion(Assets.background);
                break;

        }

    }
}







