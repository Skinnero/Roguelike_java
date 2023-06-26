package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.ui.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.GameMessageSnippet;
import com.codecool.dungeoncrawl.logic.ui.TileId;


public abstract class ActorEnemy extends Actor {
    GameMessage gameMessage = GameMessage.getInstance();
    public ActorEnemy(TileId tileId, Position position) {
        super(tileId, position);
    }

    public abstract Movement planMovement(GameMap map);

    //TODO: ATTACKING PLAYER IS STILL SOMEWHAT BUGGED
    public boolean isPlayerAttackable(GameMap gameMap, Position position) {
        int RADIUS = 1;
        int min_x = Integer.max(0, position.x() - RADIUS);
        int max_x = Integer.min(gameMap.getCells()[0].length - 1, position.x() + RADIUS);
        int min_y = Integer.max(0, position.y() - RADIUS);
        int max_y = Integer.min(gameMap.getCells().length - 1, position.y() + RADIUS);

        for (int i = min_x; i <= max_x ; i++) {
            for (int j = min_y; j <= max_y ; j++) {
                if (gameMap.getCells()[i][j].getActor() instanceof Player) {
                    return true;
                }
            }
        }
        return false;
    }

    public void attackPlayer() {
        Player player = Player.getInstance();
        player.setHealth(player.getHealth() - Math.max(getAttack() - player.getDefense(), 0));

        gameMessage.addToLogStash(GameMessageSnippet.MONSTER_DAMAGE_DONE.getMessage() + getAttack());
        gameMessage.addToLogStash(GameMessageSnippet.PLAYER_DAMAGE_TAKEN.getMessage() + Math.max(getAttack() - player.getDefense(), 0));

    }
}
