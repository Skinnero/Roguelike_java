package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;

public class Door extends InteractiveObject {


    private boolean isOpen = false;

    public Door() {
        super(InteractiveObjectTileId.CLOSED_DOOR.getTileId());
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
        setTileId(InteractiveObjectTileId.OPEN_DOOR.getTileId());
    }

    @Override
    public boolean isWalkable() {
        return isOpen;
    }
    public void setOpen(boolean open) {
        isOpen = open;
    }

}
