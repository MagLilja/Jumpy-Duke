package se.yrgo.jumpyduke;

import com.badlogic.gdx.ScreenAdapter;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.screens.MenuScreen;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(GdxTestRunner.class)
public class DukeGameTest {
    MenuScreen menuScreen;
    DukeGame dukeGame = new DukeGame();

    @Test
    @Ignore
    public void disposeDisposes() {

    }

}