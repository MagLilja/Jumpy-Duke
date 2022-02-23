package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.utils.Align;
import se.yrgo.jumpyduke.config.Configurations;

import java.util.concurrent.ThreadLocalRandom;

public class PipeDuo {
    private Pipe bottomPipe;
    private Pipe topPipe;
    private double randomPipePlacement;
    private Bug bug;

    public PipeDuo(Pipe bottomPipe, Pipe topPipe, Bug bug) {
        this.bottomPipe = bottomPipe;
        this.topPipe = topPipe;
        this.bug = bug;
    }

    public void initFirstPair() {
        randomPipePlacement = ThreadLocalRandom.current().
                nextDouble(Configurations.PIPE_GAP_BOTTOM_LEVEL, Configurations.PIPE_GAP_TOP_LEVEL);
        int xPlacement = Configurations.GAME_WIDTH;
        initializePair(xPlacement);
    }

    public void initSecondPair() {
        randomPipePlacement = ThreadLocalRandom.current().
                nextDouble(Configurations.PIPE_GAP_BOTTOM_LEVEL, Configurations.PIPE_GAP_TOP_LEVEL);
        int xPlacement = Configurations.GAME_WIDTH + Configurations.pipe_spacing;
        initializePair(xPlacement);
    }

    public void initThirdPair() {
        randomPipePlacement = ThreadLocalRandom.current().
                nextDouble(Configurations.PIPE_GAP_BOTTOM_LEVEL, Configurations.PIPE_GAP_TOP_LEVEL);
        int xPlacement = Configurations.GAME_WIDTH + (Configurations.pipe_spacing * 2);
        initializePair(xPlacement);
    }

    private void initializePair(float gameWidth) {
        topPipe.getPipeRegion().flip(true, true);
        topPipe.setPosition(gameWidth, (float) (randomPipePlacement +
                        (Configurations.gap_size / 2)), Align.bottomLeft);
        bottomPipe.setPosition(gameWidth, (float) (randomPipePlacement -
                        (Configurations.gap_size / 2)), Align.topLeft);
        bug.setPosition(bottomPipe.getX(Align.center) + Configurations.pipe_spacing / 2, (float) getBuggRandomYDisPlacement(), Align.center);
    }

    private double getBuggRandomYDisPlacement() {
        return ThreadLocalRandom.current().
                nextDouble(Configurations.BUGG_BOTTOM_LEVEL, Configurations.BUGG_TOP_LEVEL);
    }

    public void reInitialize() {
        if (bottomPipe.getX(Align.right) <= 0) {
            topPipe.getPipeRegion().flip(true, true);
            randomPipePlacement = ThreadLocalRandom.current().
                    nextDouble(Configurations.PIPE_GAP_BOTTOM_LEVEL, Configurations.PIPE_GAP_TOP_LEVEL);
            initializePair((int) (Configurations.pipe_spacing * 3 + topPipe.getX()));
        }
    }

    public Pipe getBottomPipe() {
        return bottomPipe;
    }

    public Pipe getTopPipe() {
        return topPipe;
    }

    public Bug getBugg() {
        return bug;
    }
}
