package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.utils.Align;
import se.yrgo.jumpyduke.actors.Bug;
import se.yrgo.jumpyduke.actors.Pipe;
import se.yrgo.jumpyduke.config.Configurations;

import java.util.concurrent.ThreadLocalRandom;

public class PipeDuoManager {
    private Pipe bottomPipe;
    private Pipe topPipe;
    private double randomPipePlacement;
    private Bug bug;

    public PipeDuoManager(Pipe bottomPipe, Pipe topPipe, Bug bug) {
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
        bug.setPosition(bottomPipe.getX(Align.center) + Configurations.pipe_spacing / 2, (float) getBugRandomYDisPlacement(), Align.center);
    }

    public void reInitialize() {
        if (bottomPipe.getX(Align.right) <= 0) {
            topPipe.getPipeRegion().flip(true, true);
            randomPipePlacement = ThreadLocalRandom.current().
                    nextDouble(Configurations.PIPE_GAP_BOTTOM_LEVEL, Configurations.PIPE_GAP_TOP_LEVEL);
            initializePair((int) (Configurations.pipe_spacing * 3 + topPipe.getX()));
        bug.setNewRandomXVelocity();
        }
    }

    public Pipe getBottomPipe() {
        return bottomPipe;
    }

    public Pipe getTopPipe() {
        return topPipe;
    }

    public Bug getBug() {
        return bug;
    }

    private double getBugRandomYDisPlacement() {
        return ThreadLocalRandom.current().
                nextDouble(Configurations.BUG_BOTTOM_LEVEL, Configurations.BUG_TOP_LEVEL);
    }
}
