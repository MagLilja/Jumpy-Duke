package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.utils.GameModeState;

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
        batch.draw(backgroundRegion, getX(), getY());
    }


    public void setTexture(GameModeState currentGameMode) {
        switch (currentGameMode){
            case HARD:
                backgroundRegion.setRegion(Assets.hardBackground);
                break;
            case EASY:
               // backgroundRegion.setRegion(Assets.easyBackground);
                break;
            default:
                backgroundRegion.setRegion(Assets.background);
                break;

        }

    }
}







