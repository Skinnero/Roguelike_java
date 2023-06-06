package com.codecool.dungeoncrawl.logic.ui;

public enum CellType {
    VOID("void"),
    WALKABLE("walkable"),
    UNWALKABLE("unwalkable");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
