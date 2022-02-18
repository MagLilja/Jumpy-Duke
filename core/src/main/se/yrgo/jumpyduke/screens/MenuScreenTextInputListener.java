package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Input;
import se.yrgo.jumpyduke.player.Player;

public class MenuScreenTextInputListener implements Input.TextInputListener {
    private Player player;

    public MenuScreenTextInputListener(Player player) {

        this.player = player;
    }

    @Override
    public void input(String inputUserName) {
//        if (isPlayerInList(inputUserName))
//            player.setUserName("Already in data");
//        else
            player.setUserName(inputUserName);

    }



    @Override
    public void canceled() {

    }
}
