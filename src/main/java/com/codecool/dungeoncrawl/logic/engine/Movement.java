package com.codecool.dungeoncrawl.logic.engine;

public record Movement(Position currentPosition, Position newPosition) {
    public static Movement of(Position currentPosition, Position newPosition) {
        return new Movement(currentPosition, newPosition);
    }
}
