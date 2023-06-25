package com.codecool.dungeoncrawl.logic.ui;

import lombok.Getter;

public enum GameMessageSnippet {
    PICK_ITEM("You picked up "),
    USE_ITEM("You used "),
    OPEN_UP_INTERACTIVE_OBJECT("You open up a "),
    CLOSE_DOOR("You close a "),
    TRAVEL_WITH_BOAT("You have traveled to further location with a "),
    PLAYER_DAMAGE_DONE("You hit an enemy for "),
    PLAYER_DAMAGE_TAKEN(" damage was taken from an enemy"),
    PLAYER_MOVE_INTO_UNWALKABLE_TILE("You can't move there"),
    PLAYER_PICK_NOTHING("There is nothing to pick up"),
    PLAYER_INTERACT_WITH_NOTHING("There is nothing to interact with"),
    MONSTER_DAMAGE_TAKEN(" damage was taken from a player"),
    MONSTER_DAMAGE_DONE(" hits player for ");

    @Getter
    private final String message;

    GameMessageSnippet(String message) {
        this.message = message;
    }

}
