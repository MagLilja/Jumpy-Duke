package se.yrgo.jumpyduke.player;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PLayerManagerTest {

    @Test
    void getDataFromJsonTest() throws IOException {
        // given
        List<Player> dataFromJson = PLayerManager.getDataFromJson();
        // when

        // then
        assertDoesNotThrow(() -> dataFromJson.size());
    }
}