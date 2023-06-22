package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Key;
import lombok.Setter;

public class Door extends InteractiveObject {


    private boolean isOpen = false;
    private boolean isLocked = true;

    public Door() {
        super(InteractiveObjectTileId.CLOSED_DOOR.getTileId());
    }


    @Override
    public void interact() {
        if (isLocked) {
            searchPlayerInventoryForKey();
            return;
        }
        if (isOpen) {
            setOpen(false);
            setTileId(InteractiveObjectTileId.CLOSED_DOOR.getTileId());
        } else {
            setOpen(true);
            setTileId(InteractiveObjectTileId.OPEN_DOOR.getTileId());
        }
    }
    public void searchPlayerInventoryForKey() {
        for (Item item : Player.getInstance().getInventory()) {
            if (item instanceof Key) {
                Player.getInstance().removeFromInventory(item);
                isLocked = false;
                return;
            }
        }
    }

    @Override
    public boolean isWalkable() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

}
