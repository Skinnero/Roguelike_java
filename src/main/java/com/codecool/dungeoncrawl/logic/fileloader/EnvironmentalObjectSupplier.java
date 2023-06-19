package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.TileType;

public class EnvironmentalObjectSupplier {
    private EnvironmentalObjectSupplier() {
    }

    public static Cell empty(Cell cell) {
        cell.setType(TileType.EMPTY);
        return cell;
    }

    public static Cell floor(Cell cell) {
        cell.setType(TileType.FLOOR);
        return cell;
    }

    public static Cell wall(Cell cell) {
        cell.setType(TileType.WALL);
        return cell;
    }

    public static Cell water(Cell cell) {
        cell.setType(TileType.WATER);
        return cell;
    }

    public static Cell grass(Cell cell) {
        cell.setType(TileType.GRASS);
        return cell;
    }

    public static Cell letterD(Cell cell) {
        cell.setType(TileType.LETTER_D);
        return cell;
    }

    public static Cell letterO(Cell cell) {
        cell.setType(TileType.LETTER_O);
        return cell;
    }

    public static Cell letterN(Cell cell) {
        cell.setType(TileType.LETTER_N);
        return cell;
    }

    public static Cell letterT(Cell cell) {
        cell.setType(TileType.LETTER_T);
        return cell;
    }

    public static Cell letterG(Cell cell) {
        cell.setType(TileType.LETTER_G);
        return cell;
    }

    public static Cell letterH(Cell cell) {
        cell.setType(TileType.LETTER_H);
        return cell;
    }

    public static Cell letterE(Cell cell) {
        cell.setType(TileType.LETTER_E);
        return cell;
    }
    public static Cell letterR(Cell cell) {
        cell.setType(TileType.LETTER_R);
        return cell;
    }
}
