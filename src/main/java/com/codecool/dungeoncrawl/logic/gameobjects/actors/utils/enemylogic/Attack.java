package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.ActorEnemy;

public class Attack {
    public void attackPlayer(GameMap map, ActorEnemy actor) {
        int playerHp = map.getPlayer().getHealth();
        map.getPlayer().setHealth(playerHp - (actor.getAttack() - map.getPlayer().getDefense()));
//        if (actor.isDead()) {
//            if (actor instanceof Mage) {
//                MapLoader.mages.remove(actor);
//            } else if (actor instanceof Ogre) {
//                MapLoader.ogres.remove(actor);
//            }
//        }
    }
}