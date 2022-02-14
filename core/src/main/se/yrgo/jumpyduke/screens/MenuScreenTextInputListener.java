package se.yrgo.jumpyduke.screens;

import com.badlogic.gdx.Input;
import se.yrgo.jumpyduke.player.Player;

public class MenuScreenTextInputListener implements Input.TextInputListener {
    @Override
    public void input(String inputUserName) {
      Player.setUserName(inputUserName);
    }

    @Override
    public void canceled() {

    }
}
