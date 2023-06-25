package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.ui.Renderable;
import com.codecool.dungeoncrawl.logic.ui.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.ui.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.GameMessageSnippet;

public interface Item extends Renderable {
    @Override
    TileId getTileId();
    void onUse(Player player);

    default void addMessageToLog() {
        GameMessage gameMessage = GameMessage.getInstance();
        gameMessage.addToLogStash(GameMessageSnippet.USE_ITEM.getMessage() + this.getClass().getSimpleName());
    }

}
