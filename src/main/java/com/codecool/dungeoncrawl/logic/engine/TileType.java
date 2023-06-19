package com.codecool.dungeoncrawl.logic.engine;

public enum TileType {
    EMPTY("empty", false),
    FLOOR("floor", true),
    WATER("water", false),
    WALL("wall", false),
    PLAYER("player", false),
    SKELETON("skeleton", false),
    OGRE("ogre", false),
    FOOD("food", true),
    TORCH("torch", true),
    BOAT("boat", true),
    MAGE("mage", false),
    GRASS("grass", true),
    CLOSED_CHEST("closed_chest", false),
    OPEN_CHEST("open_chest", false),
    CLOSED_DOOR("closed_door", false),
    OPEN_DOOR("open_door", true),
    SWORD("sword", true),
    KEY("key", true),
    ARMOR("armor", true),
    LETTER_D("letter_d", true),
    LETTER_O("letter_o", true),
    LETTER_N("letter_n", true),
    LETTER_T("letter_t", true),
    LETTER_G("letter_g", true),
    LETTER_H("letter_h", true),
    LETTER_E("letter_e", true),
    LETTER_R("letter_r", true);

    private final String tileName;
    private final boolean walkable;

    TileType(String tileName, boolean walkable) {
        this.tileName = tileName;
        this.walkable = walkable;
    }

    public boolean isWalkable(){
        return walkable;
    }

    public String getTileName(){
        return tileName;
    }

}
