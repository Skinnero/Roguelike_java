package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.gameobject.Gate;

public class Key extends Item {
    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public void onUse(Player player) {
        for (int[] coordinate : Util.OFFSET_COORDINATES) {
            Cell adjecentCell = player.getCell().getNeighbor(coordinate[0], coordinate[1]);
            if (adjecentCell.getGameObject() instanceof Gate && adjecentCell.getGameObject().isInteractive()) {
                adjecentCell.getGameObject().onInteraction();
            }
        }
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
