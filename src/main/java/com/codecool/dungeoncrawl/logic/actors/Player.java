package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.filemanagement.MapLoader;
import com.codecool.dungeoncrawl.logic.gameobject.GameObject;
import com.codecool.dungeoncrawl.logic.gameobject.Gate;
import com.codecool.dungeoncrawl.logic.gameobject.TraversalObject;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;
import com.codecool.dungeoncrawl.logic.items.Inventory;
import com.codecool.dungeoncrawl.logic.items.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player extends Actor {
    private final Inventory<Item> inventory = new Inventory<>();

    private final Map<String, Item> equipment = new HashMap<>();

    private int perception = 4;

    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "player";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell.getType() == CellType.UNWALKABLE) {
            return;
        } else if (!Objects.isNull(nextCell.getActor())) {
            makeAttack(nextCell);
            if (!nextCell.getActor().isDead()) {
                return;
            }
        } else if (!Objects.isNull(nextCell.getGameObject()) && nextCell.getGameObject().isInteractive()) {
            return;
        }
        getCell().setActor(null);
        nextCell.setActor(this);
        setCell(nextCell);
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
            if (adjecentCell.getGameObject() != null && adjecentCell.getGameObject().isInteractive()) {
                adjecentCell.getGameObject().onInteraction(this);
            }
        }
    }

    public void pickUpItem() {
        if (isInventoryFull() || Objects.isNull(getCell().getItem())) {
            return;
        }
        addItemToInventory(getCell().getItem());
        getCell().getItem().removeItemFromMap();
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
        if (getCell().getGameObject() instanceof TraversalObject) {
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
