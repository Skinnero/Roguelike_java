package com.codecool.dungeoncrawl.logic.gameobject;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.items.Food;

public class Chest extends GameObject{
    private String tileName = "closed_chest";

    public Chest(Cell cell) {
        super(cell);
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    @Override
    public void onInteraction(Player player) {
        setTileName("open_chest");
        player.addToInventory(new Food());
        setInteractive(false);
    }

    @Override
    public String getTileName() {
        return tileName;
    }
}
