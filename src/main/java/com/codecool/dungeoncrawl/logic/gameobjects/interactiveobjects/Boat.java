package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;
import com.codecool.dungeoncrawl.logic.ui.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.GameMessageSnippet;

public class Boat extends InteractiveObject {

    public Boat() {
        super(InteractiveObjectTileId.BOAT.getTileId());
    }


    @Override
    public void interact() {
        GameMessage gameMessage = GameMessage.getInstance();
        gameMessage.addToLogStash(GameMessageSnippet.TRAVEL_WITH_BOAT.getMessage() + this.getClass().getSimpleName());
    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
