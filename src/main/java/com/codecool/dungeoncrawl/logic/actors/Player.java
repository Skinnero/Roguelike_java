package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.gameobject.Gate;
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

    private Map<String, Item> equipment = new HashMap<>();

    private final int perception = 4;

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

//    public void interactWithGameObject() {
//        for (int[] coordinate : Util.OFFSET_COORDINATES) {
//            Cell adjecentCell = player.getCell().getNeighbor(coordinate[0], coordinate[1]);
//            if (adjecentCell.getGameObject() instanceof Gate && adjecentCell.getGameObject().isInteractive()) {
//                adjecentCell.getGameObject().onInteraction();
//            }
//        }
//    }

    public void pickUpItem() {
        if (isInventoryFull() || Objects.isNull(getCell().getItem())) {
            return;
        }
        addItemToInventory(getCell().getItem());
        getCell().getItem().removeItemFromMap();
    }

    public void useItem(int itemSlot) {
        if (inventory.getInventory().size() <= itemSlot) {
            return;
        }
        inventory.getItem(itemSlot).onUse(this);
        inventory.getInventory().remove(itemSlot);
    }

    public void addToEquipment(Item item) {
        equipment.put(item.getClass().getSimpleName(), item);
    }

    public Map<String, Item> getEquipment() {
        return equipment;
    }

    public Inventory<Item> getInventory() {
        return inventory;
    }

    public int getFieldOfView(Player player, GameMap map) {
        int baseFieldOfView = 5;
//        int attributeBonus = player.getPerception() / 2;
//        int mapSizeBonus = Math.min(map.getWidth(), map.getHeight()) / 10;

        int fieldOfView = baseFieldOfView;
//                + attributeBonus + mapSizeBonus;
        return fieldOfView;
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
