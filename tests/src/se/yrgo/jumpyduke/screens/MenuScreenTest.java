package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import se.yrgo.jumpyduke.actors.Duke;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;

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
        Vector2 v = Configurations.dukeStartingPostition;
        float yExpected = Configurations.GAME_HEIGHT / 2;
        float xExpected = Configurations.GAME_WIDTH / 2 - 44;
        // when
        assertEquals(yExpected, v.y);
        assertEquals(xExpected, v.x);
        // then
    }
}