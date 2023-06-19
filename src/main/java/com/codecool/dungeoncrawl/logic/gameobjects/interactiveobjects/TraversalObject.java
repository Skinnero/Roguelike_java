package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;

public class TraversalObject extends InteractiveObject {
    private GameMap map;

    public TraversalObject(Cell cell) {
        super(cell);
    }

    @Override
    public void onInteraction(Player player) {
        map = MapLoader.loadMap("/map" + map.getLevel() + ".txt");
        map.setLevel(map.getLevel() + 1);
    }
}
