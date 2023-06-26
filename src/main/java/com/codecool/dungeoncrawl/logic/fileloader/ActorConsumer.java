package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.Mage;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.Ogre;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.Skeleton;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class ActorConsumer {

    private ActorConsumer() {

    }

    public static void skeleton(Cell cell) {
        cell.setActor(new Skeleton(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void ogre(Cell cell) {
        cell.setActor(new Ogre(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void mage(Cell cell) {
        cell.setActor(new Mage(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }

    public static void player(Cell cell) {
        Player.getInstance().setPosition(cell.getPosition());
        cell.setActor(Player.getInstance());
    }
}
