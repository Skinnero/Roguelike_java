package com.codecool.dungeoncrawl.logic.ui;

import java.util.ArrayList;
import java.util.List;

public class GameMessage {

    private final List<String> logStash = new ArrayList<>();

    private static GameMessage single_instance = null;

    public GameMessage() {

    }

    public static GameMessage getInstance() {
        if (single_instance == null) {
            single_instance = new GameMessage();
        }
        return single_instance;
    }

    public void addToLogStash(String gameMessage) {
        logStash.add(gameMessage);
    }

    public List<String> getLogStash() {
        return new ArrayList<>(logStash);
    }

}
