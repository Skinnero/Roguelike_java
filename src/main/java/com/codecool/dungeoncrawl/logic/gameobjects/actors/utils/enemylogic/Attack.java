package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.gameobjects.actorenemies.ActorEnemy;

public class Attack {
    // TODO: I CREATED A METHOD INSIDE AN ActorEnemy CLASS JUST TO MAKE IT EASIER FOR ME TO FIX
    public void attackPlayer(GameMap map, ActorEnemy enemy) {
        int playerHp = map.getPlayer().getHealth();
        map.getPlayer().setHealth(playerHp - Math.max(enemy.getAttack() - map.getPlayer().getDefense(), 0));
    }
}