package se.yrgo.jumpyduke.player;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PLayerManagerTest {

    @Test
    void getDataFromJsonTest() throws IOException {
        // given
        PlayerManager.loadPlayerData("players.json");
        List<Player> dataFromJson = PlayerManager.getDataFromJson();
        // when

        // then
        assertDoesNotThrow(() -> dataFromJson.size());
    }
}