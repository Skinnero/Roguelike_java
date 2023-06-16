package com.codecool.dungeoncrawl.logic.engine;

public enum CellType {
    VOID("void"),
    LETTER_D("letterD"),
    LETTER_O("letterO"),
    LETTER_N("letterN"),
    LETTER_T("letterT"),
    LETTER_G("letterG"),
    LETTER_H("letterH"),
    LETTER_E("letterE"),
    LETTER_R("letterR"),
    WATER("water"),
    GRASS("grass"),
    WALKABLE("walkable"),
    UNWALKABLE("unwalkable"),
    ENEMY("enemy");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
