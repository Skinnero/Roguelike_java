package com.codecool.dungeoncrawl.fileloader.gamestateloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.Mage;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.Ogre;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.Skeleton;
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
}
