package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.yrgo.jumpyduke.DukeGame;
import se.yrgo.jumpyduke.actors.Duke;
import se.yrgo.jumpyduke.assets.Assets;

import static org.junit.jupiter.api.Assertions.*;

class MenuScreenTest {

    @BeforeAll
    static void beforeAll() {
        Assets.loadAssets();
    }

    @Test
    void shouldReturnDukeStartingPostitionAsMiddleOfWindowTest() {
        // given
        Duke duke = new Duke();
        Vector2 v = duke.getDukeStartingPostition();
        float yExpected = DukeGame.HEIGHT / 2;
        float xExpected = DukeGame.WIDTH / 2 - 38;
        // when
        assertEquals(yExpected, v.y);
        assertEquals(xExpected, v.x);
        // then
    }
}