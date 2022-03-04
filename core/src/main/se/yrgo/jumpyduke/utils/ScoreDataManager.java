package se.yrgo.jumpyduke.utils;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.player.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class ScoreDataManager {
    private static List<Player> listOfPlayers;
    private static String fileName;

    public static void loadDataFromJson(String dataFile) throws IOException {
        fileName = dataFile;
        if (Files.exists(Path.of(fileName)) && Files.size(Path.of(fileName)) != 0) {
            listOfPlayers = getDataFromJson(fileName);
        } else {
            initListOfPlayersFromEmptyTemplate();
        }
    }

    private static void initListOfPlayersFromEmptyTemplate() throws IOException {
        listOfPlayers = getDataFromJson(Assets.playersTemplateFile);
        logger.info("No playerScores.json file, will create new from template.");
    }

    public static List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public static List<Player> getDataFromJson(String jsonFile) throws IOException {
        InputStream inputStream = Resources.getResource(jsonFile).openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Player>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }

    public static void updateDataFile(Player player) throws IOException {

        Player lastRoundPlayer = new Player(player.getUserName(),
                player.getLastScore(), player.getHighScore(), player.getRounds(), player.getGameModeState());
        listOfPlayers.add(lastRoundPlayer);

        try (Writer writer = new FileWriter("playerScores.json")) {
            new Gson().toJson(getSortedLimitedListOfPlayers(), writer);
        }
        logger.info("updated playerScores.json file, will create new from template.");
    }

    private static List<Player> getSortedLimitedListOfPlayers() {
        List<Player> sortedLimitedListOfPlayers = listOfPlayers.stream()
                .sorted(Comparator.comparingInt(Player::getHighScore).reversed())
                .limit(Configurations.LIMIT_OUTPUT_LIST_TO)
                .collect(Collectors.toList());
        return sortedLimitedListOfPlayers;
    }
}