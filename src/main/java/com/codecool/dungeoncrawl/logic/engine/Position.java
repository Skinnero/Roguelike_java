package com.codecool.dungeoncrawl.logic.engine;

public record Position(int x, int y) {
    public static Position of(int x, int y) {
        return new Position(x, y);
    }
}
