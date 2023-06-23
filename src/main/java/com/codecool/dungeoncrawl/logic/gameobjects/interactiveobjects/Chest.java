package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.Util;
import com.codecool.dungeoncrawl.logic.gameobjects.items.*;

import java.util.Random;

public class Chest extends InteractiveObject {
    public Chest() {
        super(InteractiveObjectTileId.CLOSED_CHEST.getTileId());
    }

    @Override
    public void interact() {
        setTileId(InteractiveObjectTileId.OPEN_CHEST.getTileId());
        Player.getInstance().addToInventory((generateRandomItem()));
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    private Item generateRandomItem() {
        Random random = new Random();
        int index = random.nextInt(Util.classNames.length);

        switch (Util.classNames[index]) {
            case "ARMOR" -> {
                return new Armor();
            }
            case "SWORD" -> {
                return new Sword();
            }
            case "FOOD" -> {
                return new Food();
            }
            case "TORCH" -> {
                return new Torch();
            }
            default -> throw new IllegalArgumentException("Invalid class name: " + Util.classNames[index]);
        }
    }
}
