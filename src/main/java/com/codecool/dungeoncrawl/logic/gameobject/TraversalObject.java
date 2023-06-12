package com.codecool.dungeoncrawl.logic.gameobject;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.filemanagement.MapLoader;

public class TraversalObject extends GameObject {
    private final String tileName;
    private GameMap map;

    private int mapLevel;

    public TraversalObject(Cell cell, String tileName, GameMap map) {
        super(cell);
        this.tileName = tileName;
        this.map = map;
    }

    @Override
    public String getTileName() {
        return tileName;
    }

    @Override
    public void onInteraction(Player player) {
        map = MapLoader.loadMap("/map" + map.getLevel() + ".txt");
        map.setLevel(map.getLevel() + 1);
    }
}
