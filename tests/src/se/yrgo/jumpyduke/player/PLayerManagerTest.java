package se.yrgo.jumpyduke.player;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PLayerManagerTest {

    @Test
    @Disabled
    void getDataFromJsonTest() throws IOException {
        // given
        ScoreDataManager.loadDataFromJson("players.json");
        List<Player> dataFromJson = ScoreDataManager.getDataFromJson("players.json");
        // when

        // then
        assertDoesNotThrow(() -> dataFromJson.size());
    }
}