package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.fxmlController.ProfessionTileId;
import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.ui.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.GameMessageSnippet;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Player extends ActorPlayer {
    private final List<Item> inventory = new ArrayList<>();
    @Getter
    @Setter
    private int perception = 4;
    private String name;
    private static Player single_instance;

    public Player() {
        super(null, null);
    }

    public static Player getInstance() {
        if (Objects.isNull(single_instance)) {
            single_instance = new Player();
        }
        return single_instance;
    }

    public Movement planMovement(Direction direction) {
        return Movement.of(getPosition(), getNewPosition(direction));
    }

    private Position getNewPosition(Direction direction) {
        return Position.of(getPosition().x() + direction.getPosition().x(), getPosition().y() + direction.getPosition().y());
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    public void interactWithObject(GameMap map) {
        GameMessage gameMessages = GameMessage.getInstance();
        for (Position position : Arrays.stream(Direction.values()).map(Direction::getPosition).toList()) {
            Cell adjacentCell = map.getPlayerCell().getNeighbor(position, map);
            if (Objects.nonNull(adjacentCell.getInteractiveObject())) {
                adjacentCell.getInteractiveObject().interact();
                return;
            }
        }
        gameMessages.addToLogStash(GameMessageSnippet.PLAYER_INTERACT_WITH_NOTHING.getMessage());
    }

    public void pickUpItem(GameMap map) {
        GameMessage gameMessages = GameMessage.getInstance();
        if (isInventoryFull() || Objects.isNull(map.getPlayerCell().getItem())) {
            gameMessages.addToLogStash(GameMessageSnippet.PLAYER_PICK_NOTHING.getMessage());
            return;
        }
        gameMessages.addToLogStash(GameMessageSnippet.PICK_ITEM.getMessage() + map.getPlayerCell().getItem().getClass().getSimpleName());
        addToInventory(map.getPlayerCell().getItem());
        map.removeItemFromGameObjectList(map.getPlayerCell().getItem());
        map.getPlayerCell().setItem(null);
    }

    public void useItem(int itemSlot) {
        if (inventory.size() <= itemSlot) {
            return;
        }
        inventory.get(itemSlot).onUse(this);
    }

    public void setProfession(ProfessionTileId professionTileId) {
        switch (professionTileId) {
            case MAGE -> {
                setTileId(professionTileId.getTileId());
                setHealth(15);
                setDefense(1);
                setAttack(3);
            }
            case WARRIOR -> {
                setTileId(professionTileId.getTileId());
                setHealth(25);
                setDefense(3);
                setAttack(1);
            }
            case HUNTER -> {
                setTileId(professionTileId.getTileId());
                setHealth(20);
                setDefense(2);
                setAttack(2);
            }
        }
    }

    public int getFieldOfView(GameMap map) {
//        int attributeBonus = player.getPerception();
//        int mapSizeBonus = Math.min(map.getWidth(), map.getHeight()) / 10;
//        return attributeBonus + mapSizeBonus;
        return perception;
    }

    public List<Item> getInventory() {
        return new ArrayList<>(inventory);
    }

    private boolean isInventoryFull() {
        return inventory.size() >= 9;
    }

    private int calculateDamage(int enemyDefense) {
        return getAttack() - enemyDefense;
    }

    @Override
    public <T extends Actor> void planAttack(T enemy) {
        enemy.setHealth(enemy.getHealth() - calculateDamage(enemy.getDefense()));
        GameMessage gameMessage = GameMessage.getInstance();
        gameMessage.addToLogStash(GameMessageSnippet.PLAYER_DAMAGE_DONE.getMessage() + getAttack());
        gameMessage.addToLogStash(GameMessageSnippet.MONSTER_DAMAGE_TAKEN.getMessage() + calculateDamage(enemy.getDefense()));
    }
}
