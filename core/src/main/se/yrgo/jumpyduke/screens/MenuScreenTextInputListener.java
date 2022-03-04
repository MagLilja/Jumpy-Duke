package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Input;
import se.yrgo.jumpyduke.player.Player;

import static se.yrgo.jumpyduke.utils.GameUtils.logger;

public class MenuScreenTextInputListener implements Input.TextInputListener {
    private Player player;

    public MenuScreenTextInputListener(Player player) {
        this.player = player;
    }

    @Override
    public void input(String inputUserName) {
        player.setUserName(inputUserName);
        logger.info("Player name set to: " + inputUserName);
    }

    @Override
    public void canceled() {
    }
}