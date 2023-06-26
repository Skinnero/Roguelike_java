package com.codecool.dungeoncrawl.logic.engine;

/**
 * Record that represents typical problem where we use int[] and then getting values by its index
 * @param x position on array
 * @param y position on array
 */
public record Position(int x, int y) {
    public static Position of(int x, int y) {
        return new Position(x, y);
    }
}
