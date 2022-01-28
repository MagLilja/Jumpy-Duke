package se.yrgo.jumpyduke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import se.yrgo.jumpyduke.assets.Assets;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(GdxTestRunner.class)
class DukeGameTest {
    @BeforeAll
    static void beforeAll() {
        Assets.loadAssets();
    }

    @Test
    void create() {
    }

    @Test
    void dispose() {
        Assets.dispose();
        Assets.duke.getRegionWidth();
        assertEquals(1,1);
    }
}