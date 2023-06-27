package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Key;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.gamemessage.GameMessageSnippet;

public class Door extends InteractiveObject {
    private boolean isOpen = false;
    private boolean isLocked = true;
    private final GameMessage gameMessage = GameMessage.getInstance();


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
            gameMessage.addToLogStash(GameMessageSnippet.CLOSE_DOOR.getMessage() + this.getClass().getSimpleName());
        } else {
            setOpen(true);
            setTileId(InteractiveObjectTileId.OPEN_DOOR.getTileId());
            gameMessage.addToLogStash(GameMessageSnippet.OPEN_UP_INTERACTIVE_OBJECT.getMessage() + this.getClass().getSimpleName());

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
        gameMessage.addToLogStash(GameMessageSnippet.CANT_OPEN_DOOR.getMessage());
    }

    @Override
    public boolean isWalkable() {
        return isOpen;
    }
    public void setOpen(boolean open) {
        isOpen = open;
    }

}
