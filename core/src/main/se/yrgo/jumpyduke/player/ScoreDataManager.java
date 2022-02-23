package se.yrgo.jumpyduke.player;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import se.yrgo.jumpyduke.config.Configurations;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreDataManager {
    private static List<Player> listOfPLayers;
    private static String fileName;

    public static void loadDataFromJson(String dataFile) throws IOException {
        fileName = dataFile;

        if (Files.exists(Path.of(fileName)) && Files.size(Path.of(fileName)) != 0) {
            listOfPLayers = getDataFromJson(fileName);
        }
        else {
            generateNewPlayerScoreJsonDataFile();
        }
    }

    private static void generateNewPlayerScoreJsonDataFile() throws IOException {
        listOfPLayers = getDataFromJson("playersTemplate.json");
    }

    public static String getFileName() {
        return fileName;
    }

    public static List<Player> getListOfPLayers() {
        return listOfPLayers;
    }

    public static List<Player> getDataFromJson(String jsonFile) throws IOException {
        InputStream inputStream = Resources.getResource(jsonFile).openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Player>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }

    private static boolean isPlayerInList(String inputUserName) {
        return ScoreDataManager.getListOfPLayers().stream()
                .map(player -> player.getUserName())
                .anyMatch(username -> username.toLowerCase().equals(inputUserName.toLowerCase()));
    }

    public static void updateDataFile(Player player) throws IOException {
        listOfPLayers.add(player);
        List<Player> sortedLimitedListOfPlayers = listOfPLayers.stream()
                .sorted(Comparator.comparingInt(Player::getHighScore).reversed())
                .limit(Configurations.LIMIT_OUTPUT_LIST_TO)
                .collect(Collectors.toList());
        try (Writer writer = new FileWriter("players.json")) {
            new Gson().toJson(sortedLimitedListOfPlayers, writer);
        }

    }

    public static Player getPlayer() {
//        if(isPlayerInList())
        return getPlayer();
    }
}
