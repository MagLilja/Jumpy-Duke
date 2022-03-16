package se.yrgo.jumpyduke.utils;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import se.yrgo.jumpyduke.assets.Assets;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.player.Player;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    private static HttpURLConnection conn;
    private static boolean hasConnectionToAPI = true;
//    private static BufferedReader reader;

    public static void loadDataFromJson(String dataFile) throws IOException {
        fileName = dataFile;
        if (Files.exists(Path.of(fileName)) && Files.size(Path.of(fileName)) != 0) {
            listOfPlayers = getDataFromJson(fileName);
        } else {
            initListOfPlayersFromEmptyTemplate();
        }


    }

    private static String getDataStringFromAPI() {
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            URL url = new URL("https://jumpyduke.com/node-test/");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();

            if (status == 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                logger.info("No connection!");
                hasConnectionToAPI = false;
            }
            logger.info("response code: " + status);
            return responseContent.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }


    private static void initListOfPlayersFromEmptyTemplate() throws IOException {
        listOfPlayers = getDataFromJson(Assets.playersTemplateFile);
        logger.info("No playerScores.json file, will create new from template.");
    }

    public static List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    private static List<Player> getDataFromJson(String jsonFile) throws IOException {
        String json;
        if (hasConnectionToAPI) {
            json = getDataStringFromAPI();
        } else {
            InputStream inputStream = Resources.getResource(jsonFile).openStream();
            json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        }

        Type listType = new TypeToken<ArrayList<Player>>() {
        }.getType();

        List<Player> playerList = new Gson().fromJson(json, listType);
        logger.info("PlayerList: " + playerList.toString());
        return playerList;
    }

    public static void updateDataFile(Player player) throws IOException {

        Player lastRoundPlayer = new Player(player.getUserName(),
                player.getLastScore(), player.getHighScore(), player.getRounds(), player.getGameModeState());
        listOfPlayers.add(lastRoundPlayer);

        if (hasConnectionToAPI) {

        }else {
            try (Writer writer = new FileWriter(Assets.playerScoresFile)) {
                new Gson().toJson(getSortedLimitedListOfPlayers(), writer);
            }
            logger.info("updated playerScores.json file");
        }
    }

    private static List<Player> getSortedLimitedListOfPlayers() {
        List<Player> sortedLimitedListOfPlayers = listOfPlayers.stream()
                .sorted(Comparator.comparingInt(Player::getHighScore).reversed())
                .limit(Configurations.LIMIT_OUTPUT_LIST_TO)
                .collect(Collectors.toList());
        return sortedLimitedListOfPlayers;
    }
}