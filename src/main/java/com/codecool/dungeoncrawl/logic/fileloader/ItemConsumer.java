package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.gameobjects.items.*;

public class ItemConsumer {
    private ItemConsumer() {

    }

    public static void food(Cell cell) {
        cell.setItem(new Food());
    }

    public static void torch(Cell cell) {
        cell.setItem(new Torch());
    }

    public static void sword(Cell cell) {
        cell.setItem(new Sword());
    }

    public static void armor(Cell cell) {
        cell.setItem(new Armor());
    }

    public static void  key(Cell cell) {
        cell.setItem(new Key());
    }

}
