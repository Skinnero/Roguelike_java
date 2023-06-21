package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.TileId;

public class Door extends InteractiveObject {


    private boolean isOpen = false;

    public Door() {
        super(TileId.of(3, 4));
    }


    @Override
    public void interact() {
        if (isOpen) {
            return;
        }
//        for (Item item : player.getInventory().getInventory()) {
//            if (item instanceof Key) {
//                setInteractive(false);
//                player.getInventory().getInventory().remove(item);
//                return;
//            }
//        }
        setOpen(true);
        setTileId(TileId.of(4, 4));
    }

    @Override
    public boolean isWalkable() {
        return isOpen;
    }
    public void setOpen(boolean open) {
        isOpen = open;
    }

}
