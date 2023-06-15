package com.codecool.dungeoncrawl.logic.gameobject;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.filemanagement.MapLoader;

public class TraversalObject extends GameObject {
    private GameMap map;

    public TraversalObject(Cell cell) {
        super(cell);
        this.setInteractive(false);
    }

    @Override
    public String getTileName() {
        return "water";
    }

    @Override
    public void onInteraction(Player player) {
        map = MapLoader.loadMap("/map" + map.getLevel() + ".txt");
        map.setLevel(map.getLevel() + 1);
    }
}
