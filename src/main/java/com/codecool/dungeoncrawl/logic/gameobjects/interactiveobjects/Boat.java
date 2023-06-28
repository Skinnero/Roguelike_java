package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessageSnippet;

public class Boat extends InteractiveObject {
    private final GameMessage gameMessage = GameMessage.getInstance();

    public Boat() {
        super(InteractiveObjectTileId.BOAT.getTileId());
    }


    @Override
    public void interact() {
        gameMessage.addToLogStash(GameMessageSnippet.TRAVEL_WITH_BOAT.getMessage() + this.getClass().getSimpleName());
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
