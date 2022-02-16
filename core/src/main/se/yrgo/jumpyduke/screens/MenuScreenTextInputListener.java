package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Input;
import se.yrgo.jumpyduke.player.Player;

public class MenuScreenTextInputListener implements Input.TextInputListener {
    private MenuScreen menuScreen;

    public MenuScreenTextInputListener(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    @Override
    public void input(String inputUserName) {



        menuScreen.setPlayerName(inputUserName);
    }

//    private boolean isPlayerInList() {
//        listOfPlayers.stream().map(player -> player.getUserName()).filter()
//    }

    @Override
    public void canceled() {

    }
}
