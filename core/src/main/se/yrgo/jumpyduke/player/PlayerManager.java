package se.yrgo.jumpyduke.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.yrgo.jumpyduke.config.Configurations;
import se.yrgo.jumpyduke.config.DatabaseService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerManager {
    private static List<Player> listOfPLayers;
    private static String fileName;

    public static void loadDataFromJson(String dataFile) throws IOException {
        fileName = dataFile;
        //listOfPLayers = getDataFromJson();
        listOfPLayers = getDataFromDb();
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
        return PlayerManager.getListOfPLayers().stream()
                .map(player -> player.getUserName())
                .anyMatch(username -> username.toLowerCase().equals(inputUserName.toLowerCase()));
    }

    public static void updateDataFile(Player player) throws IOException {
        if (listOfPLayers.contains(player)){
            listOfPLayers.remove(player);
        }
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

    public static List<Player> getDataFromDb() {
        Session session = DatabaseService.getCurrentSessionFromConfig();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        criteriaQuery.from(Player.class);
        List<Player> resultList = session.createQuery(criteriaQuery).getResultList();
        return resultList;
    }

    public static void updateDataToDb() {
        Session session= DatabaseService.getCurrentSessionFromConfig();
        Transaction transaction = session.beginTransaction();

        for (Player player: listOfPLayers) {
            session.save(player);
        }

        transaction.commit();
        session.close();

    }
}
