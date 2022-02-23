package sound;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class SoundMusic extends Game {
    Music music;

    public void playMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("MenuScreenMusic.mp3"));
        music.play();


    }

    public Music getMusic() {
        return music;
    }

    @Override
    public void create() {
        music =  Gdx.audio.newMusic(Gdx.files.absolute("MenuScreenMusic.mp3"));
        music.play();


    }
    @Override
    public void dispose(){
        music.dispose();
    }

}
