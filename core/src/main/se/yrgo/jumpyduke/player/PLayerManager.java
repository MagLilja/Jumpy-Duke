package se.yrgo.jumpyduke.player;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PLayerManager {
    private static List<Player> listOfPLayers;
    private static String fileName;

    public static void loadDataFromJson(String dataFile) throws IOException {
        fileName = dataFile;
        listOfPLayers = getDataFromJson();
    }

    public static String getFileName() {
        return fileName;
    }

    public static List<Player> getListOfPLayers() {
        return listOfPLayers;
    }

    public static List<Player> getDataFromJson() throws IOException {
        InputStream inputStream = Resources.getResource(fileName).openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Player>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }

    private static boolean isPlayerInList(String inputUserName) {
        return PLayerManager.getListOfPLayers().stream()
                .map(player -> player.getUserName())
                .anyMatch(username -> username.toLowerCase().equals(inputUserName.toLowerCase()));
    }

    public static void updateDataFile(Player player) throws IOException {
        listOfPLayers.add(player);
        try (Writer writer = new FileWriter("players.json")) {
            new Gson().toJson(listOfPLayers, writer);
        }

    }

    public static Player getPlayer() {
//        if(isPlayerInList())
        return getPlayer();
    }
}
