package se.yrgo.jumpyduke.assets;


import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.BeforeAll;


import com.badlogic.gdx.Gdx;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import se.yrgo.jumpyduke.GdxTestRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(GdxTestRunner.class)
class AssetsTest {

    @Test
    void textureAtlasIsNotEmptyTest() {
        Assets.loadAssets();
        // when
        boolean hasTextures = Assets.textureAtlas.getTextures().notEmpty();
        // then
        assertTrue(hasTextures);

    }


    @Test
//    @Disabled
    void shoulDisposeTextureAtlasTest() {
        // given
        Assets.loadAssets();
        Assets.dispose();

        // when
        boolean hasTextures = Assets.textureAtlas.getTextures().notEmpty();
        // then
        assertFalse(hasTextures);
    }
}