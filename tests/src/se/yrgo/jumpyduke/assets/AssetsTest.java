package se.yrgo.jumpyduke.assets;


import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import se.yrgo.jumpyduke.GdxTestRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(GdxTestRunner.class)
public class AssetsTest {


    @Test
    public void loadAssetsTest() {
        // given
        Assets.loadAssets();
        // when
        // then
        assertNotNull(Assets.duke.getRegionWidth());

    }

    @Test
    public void badlogicLogoFileDoesNotExists() {
        Assert.assertFalse("This test will only pass when the badlogic.jpg file coming with a new project setup has not been deleted.", Gdx.files
                .internal("../android/assets/badlogic.jpg").exists());
    }


    @Test
    public void dispose() {
        // given

        // when

        // then
    }


}