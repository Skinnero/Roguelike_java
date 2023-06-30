package com.codecool.dungeoncrawl.fileloader.gamestateloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.items.*;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.ItemModel;

public class ItemLoaderConsumer {
    private ItemLoaderConsumer() {
    }

    public static void food(BaseModel baseModel, Cell cell) {
        cell.setItem(new Food(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
    }

    public static void sword(BaseModel baseModel, Cell cell) {
        Sword sword = new Sword(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y()));
        cell.setItem(sword);
    }

    public static void armor(BaseModel baseModel, Cell cell) {
        cell.setItem(new Armor(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
    }

    public static void key(BaseModel baseModel, Cell cell) {
        cell.setItem(new Key(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
    }

    public static void torch(BaseModel baseModel, Cell cell) {
        cell.setItem(new Torch(Position.of(baseModel.getPosition().x(), baseModel.getPosition().y())));
    }

}
