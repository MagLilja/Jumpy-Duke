package se.yrgo.jumpyduke.player;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

    public static void updateDataFile() throws IOException {
        Gson gson = new Gson();
        gson.toJson(listOfPLayers, new FileWriter(fileName));
    }
}
