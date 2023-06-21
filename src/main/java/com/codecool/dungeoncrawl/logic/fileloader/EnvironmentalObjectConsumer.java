package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.TileType;

public class EnvironmentalObjectConsumer {
    private EnvironmentalObjectConsumer() {
    }

    public static void empty(Cell cell) {
        cell.setType(TileType.EMPTY);
    }

    public static void floor(Cell cell) {
        cell.setType(TileType.FLOOR);
    }

    public static void wall(Cell cell) {
        cell.setType(TileType.WALL);
    }

    public static void water(Cell cell) {
        cell.setType(TileType.WATER);
    }

    public static void grass(Cell cell) {
        cell.setType(TileType.GRASS);
    }

    public static void letterD(Cell cell) {
        cell.setType(TileType.LETTER_D);
    }

    public static void letterO(Cell cell) {
        cell.setType(TileType.LETTER_O);
    }

    public static void letterN(Cell cell) {
        cell.setType(TileType.LETTER_N);
    }

    public static void letterT(Cell cell) {
        cell.setType(TileType.LETTER_T);
    }

    public static void letterG(Cell cell) {
        cell.setType(TileType.LETTER_G);
    }

    public static void letterH(Cell cell) {
        cell.setType(TileType.LETTER_H);
    }

    public static void letterE(Cell cell) {
        cell.setType(TileType.LETTER_E);
    }
    public static void letterR(Cell cell) {
        cell.setType(TileType.LETTER_R);
    }
}
