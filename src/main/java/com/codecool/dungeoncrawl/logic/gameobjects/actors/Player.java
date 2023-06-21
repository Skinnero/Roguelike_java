package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorutils.Direction;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Inventory;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player extends ActorPlayer {
    private final Inventory<Item> inventory = new Inventory<>();
    private final Map<String, Item> equipment = new HashMap<>();
    private int perception = 4;


    private String name;
    private static Player playerInstance;


    public String getName() {
        return name;
    }

    public Player(Position position) {
        super(TileId.of(27, 0), position);
    }

    public static Player getInstance(Position position) {
        if (playerInstance == null) {
            playerInstance = new Player(position);
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
        inventory.addItem(item);
    }

    public void removeFromInventory(Item item) {
        inventory.removeItem(item);
    }

    public void increasePerception(int perceptionValue) {
        this.perception += perceptionValue;
    }
    public void increaseHealth(int healthValue) {
        setHealth(getHealth() + healthValue);
    }

    public void interactWithObject() {
//        for (int[] coordinate : Util.OFFSET_COORDINATES) {
//            Cell adjecentCell = getCell().getNeighbor(coordinate[0], coordinate[1]);
//            if (adjecentCell.getInteractiveObject() != null && adjecentCell.getInteractiveObject().isInteractive()) {
//                adjecentCell.getInteractiveObject().onInteraction(this);
//            }
//        }
    }

    public void pickUpItem(Cell cell) {
        if (isInventoryFull() || Objects.isNull(cell.getItem())) {
            return;
        }
        addItemToInventory(cell.getItem());
        cell.setItem(null);
    }

//    public GameMap moveToAnotherLevel(int level) {
//        return MapLoader.loadMap("/map" + level + ".txt");
//    }

    public void useItem(int itemSlot) {
        if (inventory.getInventory().size() <= itemSlot) {
            return;
        }
        inventory.getItem(itemSlot).onUse(this);
    }

    public void addToEquipment(Item item) {
        if (equipment.containsKey(item.getClass().getSimpleName())) {
            equipment.replace(item.getClass().getSimpleName(), item);
        } else {
           equipment.put(item.getClass().getSimpleName(), item);
        }
    }

    public Map<String, Item> getEquipment() {
        return equipment;
    }

    public Inventory<Item> getInventory() {
        return inventory;
    }

    public int getFieldOfView(Player player, GameMap map) {
//        int attributeBonus = player.getPerception();
//        int mapSizeBonus = Math.min(map.getWidth(), map.getHeight()) / 10;
//        return attributeBonus + mapSizeBonus;
        return player.getPerception();
    }

    public int getPerception() {
        return perception;
    }



    private void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    private boolean isInventoryFull() {
        return inventory.isInventoryFull();
    }

    private void makeAttack(Cell cell) {
        cell.getActor().setHealth(cell.getActor().getHealth() - getAttack());
    }

}
