package com.codecool.dungeoncrawl.logic.gameobject;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

public class Gate extends GameObject {

    private String tileName = "closed_gate";

    public Gate(Cell cell) {
        super(cell);
        setInteractive(true);
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    @Override
    public void onInteraction(Player player) {
        for (Item item : player.getInventory().getInventory()) {
            if (item instanceof Key) {
                setTileName("open_gate");
                setInteractive(false);
                player.getInventory().getInventory().remove(item);
                return;
            }
        }
    }

    @Override
    public String getTileName() {
        return tileName;
    }
}
