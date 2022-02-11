package se.yrgo.jumpyduke.actors;

import com.badlogic.gdx.utils.Align;
import se.yrgo.jumpyduke.config.Configurations;

import java.util.concurrent.ThreadLocalRandom;

public class PipeManager {
    private Pipe bottomPipe;
    private Pipe topPipe;
    private double randomPipePlacement;

    public PipeManager(Pipe bottomPipe, Pipe topPipe) {
        this.bottomPipe = bottomPipe;
        this.topPipe = topPipe;
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
        int xPlacement = Configurations.GAME_WIDTH + Configurations.PIPE_SPACING;
        initializePair(xPlacement);
    }

    public void initThirdPair() {
        randomPipePlacement = ThreadLocalRandom.current().
                nextDouble(Configurations.PIPE_GAP_BOTTOM_LEVEL, Configurations.PIPE_GAP_TOP_LEVEL);
        int xPlacement = Configurations.GAME_WIDTH + (Configurations.PIPE_SPACING * 2);
        initializePair(xPlacement);
    }

    private void initializePair(int gameWidth) {
        topPipe.getPipeRegion().flip(true, true);
        topPipe.setPosition(gameWidth, (float) (randomPipePlacement +
                (Configurations.GAP_SIZE / 2)), Align.bottomLeft);
        bottomPipe.setPosition(gameWidth, (float) (randomPipePlacement -
                (Configurations.GAP_SIZE / 2)), Align.topLeft);
    }

    public void reInitialize() {
        if (bottomPipe.getX(Align.right) <= 0) {
            topPipe.getPipeRegion().flip(true, true);
            randomPipePlacement = ThreadLocalRandom.current().
                    nextDouble(Configurations.PIPE_GAP_BOTTOM_LEVEL, Configurations.PIPE_GAP_TOP_LEVEL);
            initializePair(Configurations.GAP_SIZE + 140 + Configurations.GAME_WIDTH);
        }
    }

    public Pipe getBottomPipe() {
        return bottomPipe;
    }

    public Pipe getTopPipe() {
        return topPipe;
    }
}
