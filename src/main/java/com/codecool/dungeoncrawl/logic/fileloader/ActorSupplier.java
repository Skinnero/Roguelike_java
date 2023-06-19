package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Mage;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Ogre;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.TileType;

public class ActorSupplier {

    private ActorSupplier() {

    }

    public static Cell skeleton(Cell cell) {
        cell.setActor(new Skeleton(cell));
        cell.setType(TileType.SKELETON);
        return cell;
    }

    public static Cell ogre(Cell cell) {
        cell.setActor(new Ogre(cell));
        cell.setType(TileType.OGRE);
        return cell;
    }

    public static Cell mage(Cell cell) {
        cell.setActor(new Mage(cell));
        cell.setType(TileType.MAGE);
        return cell;
    }

    public static Cell player(Cell cell) {
        Player.getInstance().setCell(cell);
        cell.setActor(Player.getInstance());
        cell.setType(TileType.PLAYER);
        return cell;
    }
}
