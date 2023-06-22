package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;

public abstract class ActorEnemy extends Actor{
    public ActorEnemy(TileId tileId, Position position) {
        super(tileId, position);
    }

    public abstract Movement planMovement(GameMap map);

//    public void move(GameMap map, Position position) {
//        Cell nextCell = map.getCell(position);
//        if (!nextCell.isWalkable()) {
//            return;
//        }
////        map.getCell(Position.of(dx,dy)).setActor(null);
////        nextCell.setActor(this);
//    }

    public boolean isPlayerAttackable(GameMap gameMap, Position position, int radius) {
        int min_x = Integer.max(0, position.x() - radius);
        int max_x = Integer.max(gameMap.getCells()[0].length - 1, position.x() + radius);
        int min_y = Integer.max(0, position.y() - radius);
        int max_y = Integer.max(gameMap.getCells().length - 1, position.y() + radius);

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
    }
}
