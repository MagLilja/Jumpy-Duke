package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Input;
import se.yrgo.jumpyduke.player.PLayerManager;
import se.yrgo.jumpyduke.player.Player;

public class MenuScreenTextInputListener implements Input.TextInputListener {
    private Player player;

    public MenuScreenTextInputListener(Player player) {

        this.player = player;
    }

    @Override
    public void input(String inputUserName) {
        if (isPlayerInList(inputUserName))
            player.setUserName("Already in data");
        else player.setUserName(inputUserName);

    }

    private boolean isPlayerInList(String inputUserName) {
        return PLayerManager.getListOfPLayers().stream()
                .map(player -> player.getUserName())
                .anyMatch(username -> username.toLowerCase().equals(inputUserName.toLowerCase()));
    }

    @Override
    public void canceled() {

    }
}
