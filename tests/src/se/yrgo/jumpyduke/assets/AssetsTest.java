package se.yrgo.jumpyduke.assets;


import static org.junit.Assert.assertTrue;




import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import se.yrgo.jumpyduke.GdxTestRunner;

import static org.junit.jupiter.api.Assertions.*;

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