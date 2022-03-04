package se.yrgo.jumpyduke.utils;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

public class SoundManager {
    private static Sound bugSound;
    private static Sound jumpSound;
    private static Sound collisionSound;
    private static Music backgroundMusic;

    public static void initSounds() {
        bugSound = Assets.bugSound;
        collisionSound = Assets.collisionSound;
        jumpSound = Assets.jumpSound;
        backgroundMusic = Assets.backgroundMusic;
    }

    public static void playBugSound() {
        bugSound.play(Configurations.SOUND_BUG_VOLUME);
    }

    public static void playJumpSound() {
        jumpSound.play(Configurations.SOUND_JUMP_VOLUME);
    }

    public static void playCollisionSound() {
        collisionSound.play(Configurations.SOUND_COLLISION_VOLUME);
    }

    public static void playBackgroundMusic() {
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(Configurations.SOUND_MUSIC_VOLUME);
        backgroundMusic.play();
    }
}
