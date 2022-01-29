package se.yrgo.jumpyduke;

import com.badlogic.gdx.ScreenAdapter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.screens.MenuScreen;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(GdxTestRunner.class)
public class DukeGameTest extends ScreenAdapter {
     MenuScreen menuScreen;
     DukeGame dukeGame = new DukeGame();;

//    @BeforeClass
//    public void beforeClass() throws Exception {
//
//        menuScreen = new MenuScreen(dukeGame);
//    }

    @Test
    public void disposeDisposes() {
//        dukeGame.dispose();
        assertThrows(NullPointerException.class, () -> Assets.duke.getRegionWidth());
    }

}