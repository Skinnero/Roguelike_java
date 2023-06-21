package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.Renderable;
import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;

public interface Item extends Renderable {
    @Override
    TileId getTileId();
    void onUse(Player player);

}
