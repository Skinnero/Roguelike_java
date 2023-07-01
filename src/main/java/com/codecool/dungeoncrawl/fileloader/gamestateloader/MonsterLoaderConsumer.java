package com.codecool.dungeoncrawl.fileloader.gamestateloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.*;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.MonsterModel;

public class MonsterLoaderConsumer {
    private MonsterLoaderConsumer() {
    }

    public static void skeleton(BaseModel baseModel, Cell cell) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Skeleton skeleton = new Skeleton(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        skeleton.setHealth(monsterModel.getHealth());
        skeleton.setDefense(monsterModel.getDefense());
        skeleton.setAttack(monsterModel.getAttack());
        cell.setActor(skeleton);
    }

    public static void mage(BaseModel baseModel, Cell cell) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Mage mage = new Mage(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        mage.setHealth(monsterModel.getHealth());
        mage.setDefense(monsterModel.getDefense());
        mage.setAttack(monsterModel.getAttack());
        cell.setActor(mage);
    }

    public static void ogre(BaseModel baseModel, Cell cell) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Ogre ogre = new Ogre(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        ogre.setHealth(monsterModel.getHealth());
        ogre.setDefense(monsterModel.getDefense());
        ogre.setAttack(monsterModel.getAttack());
        cell.setActor(ogre);
    }
    public static void paladin(BaseModel baseModel, Cell cell) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Paladin paladin = new Paladin(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        paladin.setHealth(monsterModel.getHealth());
        paladin.setDefense(monsterModel.getDefense());
        paladin.setAttack(monsterModel.getAttack());
        cell.setActor(paladin);
    }
    public static void warrior(BaseModel baseModel, Cell cell) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Warrior warrior = new Warrior(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        warrior.setHealth(monsterModel.getHealth());
        warrior.setDefense(monsterModel.getDefense());
        warrior.setAttack(monsterModel.getAttack());
        cell.setActor(warrior);
    }
    public static void archer(BaseModel baseModel, Cell cell) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        Archer archer = new Archer(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        archer.setHealth(monsterModel.getHealth());
        archer.setDefense(monsterModel.getDefense());
        archer.setAttack(monsterModel.getAttack());
        cell.setActor(archer);
    }
    public static void archMage(BaseModel baseModel, Cell cell) {
        MonsterModel monsterModel = (MonsterModel) baseModel;
        ArchMage archMage = new ArchMage(Position.of(monsterModel.getPositionX(), monsterModel.getPositionY()));
        archMage.setHealth(monsterModel.getHealth());
        archMage.setDefense(monsterModel.getDefense());
        archMage.setAttack(monsterModel.getAttack());
        cell.setActor(archMage);
    }
}
