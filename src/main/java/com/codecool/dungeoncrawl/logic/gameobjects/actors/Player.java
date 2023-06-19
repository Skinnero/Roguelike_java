package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.TraversalObject;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Inventory;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player extends Actor {
    private final Inventory<Item> inventory = new Inventory<>();

    private final Map<String, Item> equipment = new HashMap<>();

    private int perception = 4;

    private static Player playerInstance;

    public Player() {

    }

    public Player(Cell cell) {
        super(cell);
    }

    public static Player getInstance() {
        if (playerInstance == null) {
            playerInstance = new Player();
        }
        return playerInstance;
    }

    @Override
    public void move(int dx, int dy) {
//        Cell nextCell = getCell().getNeighbor(dx, dy);
//        makeAttack(nextCell);

//        if (nextCell.isWalkable()) {
//            nextCell.setActor(this);
//        }

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

    public void interactWithGameObject() {
        for (int[] coordinate : Util.OFFSET_COORDINATES) {
            Cell adjecentCell = getCell().getNeighbor(coordinate[0], coordinate[1]);
            if (adjecentCell.getInteractiveObject() != null && adjecentCell.getInteractiveObject().isInteractive()) {
                adjecentCell.getInteractiveObject().onInteraction(this);
            }
        }
    }

    public void pickUpItem() {
        if (isInventoryFull() || Objects.isNull(getCell().getItem())) {
            return;
        }
        addItemToInventory(getCell().getItem());
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

    public GameMap moveToNextLevel(int mapLevel, GameMap map) {
        if (getCell().getInteractiveObject() instanceof TraversalObject) {
            return MapLoader.loadMap("/map" + mapLevel + ".txt");
        }
        return map;
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
