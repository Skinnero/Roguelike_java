package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Player extends ActorPlayer {
    private final List<Item> inventory = new ArrayList<>();
    private final Map<String, Item> equipment = new HashMap<>();
    @Getter
    @Setter
    private int perception = 4;
    private String name;
    private static Player playerInstance;

    public Player() {
        super(ActorTileId.PLAYER.getTileId(), null);
    }

    public static Player getInstance() {
        if (playerInstance == null) {
            playerInstance = new Player();
        }
        return playerInstance;
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
        for (Position position : Arrays.stream(Direction.values()).map(Direction::getPosition).toList()) {
            Cell adjacentCell = map.getPlayerCell().getNeighbor(position, map);
            if (Objects.nonNull(adjacentCell.getInteractiveObject())) {
                adjacentCell.getInteractiveObject().interact();
            }
        }
    }

    public void pickUpItem(GameMap map) {
        if (isInventoryFull() || Objects.isNull(map.getPlayerCell().getItem())) {
            return;
        }
        addToInventory(map.getPlayerCell().getItem());
        map.removeItemFromGameObjectList(map.getPlayerCell().getItem());
        map.getPlayerCell().setItem(null);
    }

//    public GameMap moveToAnotherLevel(int level) {
//        return MapLoader.loadMap("/map" + level + ".txt");
//    }

    public void useItem(int itemSlot) {
        if (inventory.size() <= itemSlot) {
            return;
        }
        inventory.get(itemSlot).onUse(this);
    }

    public void addToEquipment(Item item) {
        if (equipment.containsKey(item.getClass().getSimpleName())) {
            equipment.replace(item.getClass().getSimpleName(), item);
        } else {
            equipment.put(item.getClass().getSimpleName(), item);
        }
    }

    public int getFieldOfView(Player player, GameMap map) {
//        int attributeBonus = player.getPerception();
//        int mapSizeBonus = Math.min(map.getWidth(), map.getHeight()) / 10;
//        return attributeBonus + mapSizeBonus;
        return player.getPerception();
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

    }
}
