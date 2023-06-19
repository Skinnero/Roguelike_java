package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.TileType;
import com.codecool.dungeoncrawl.logic.gameobjects.items.*;
import com.codecool.dungeoncrawl.logic.items.*;

public class ItemSupplier {
    private ItemSupplier() {

    }

    public static Cell food(Cell cell) {
        cell.setItem(new Food(cell));
        cell.setType(TileType.FOOD);
        return cell;
    }

    public static Cell torch(Cell cell) {
        cell.setItem(new Torch(cell));
        cell.setType(TileType.TORCH);
        return cell;
    }

    public static Cell sword(Cell cell) {
        cell.setItem(new Sword(cell));
        cell.setType(TileType.SWORD);
        return cell;
    }

    public static Cell armor(Cell cell) {
        cell.setItem(new Armor(cell));
        cell.setType(TileType.ARMOR);
        return cell;
    }

    public static Cell key(Cell cell) {
        cell.setItem(new Key(cell));
        cell.setType(TileType.KEY);
        return cell;
    }

}
