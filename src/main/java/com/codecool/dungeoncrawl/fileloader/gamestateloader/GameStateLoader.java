package com.codecool.dungeoncrawl.fileloader.gamestateloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.ItemModel;

import java.util.function.BiConsumer;

public enum GameStateLoader {
    FOOD(ItemLoaderConsumer::food),
    SWORD(ItemLoaderConsumer::sword),
    TORCH(ItemLoaderConsumer::torch),
    ARMOR(ItemLoaderConsumer::armor),
    KEY(ItemLoaderConsumer::key),
    DOOR(InteractiveObjectLoaderConsumer::door),
    CHEST(InteractiveObjectLoaderConsumer::chest),
    BOAT(InteractiveObjectLoaderConsumer::boat),
    OGRE(MonsterLoaderConsumer::ogre),
    MAGE(MonsterLoaderConsumer::mage),
    PALADIN(MonsterLoaderConsumer::paladin),
    WARRIOR(MonsterLoaderConsumer::warrior),
    ARCHER(MonsterLoaderConsumer::archer),
    ARCHMAGE(MonsterLoaderConsumer::archMage),
    SKELETON(MonsterLoaderConsumer::skeleton);

    private final BiConsumer<BaseModel, Cell> strategy;

    GameStateLoader(BiConsumer<BaseModel, Cell> strategy) {
        this.strategy = strategy;
    }

    public <T extends BaseModel> void apply(T baseModel, Cell cell) {
        strategy.accept(baseModel, cell);
    }
}
