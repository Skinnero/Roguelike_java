package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;
import com.codecool.dungeoncrawl.logic.items.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player extends Actor {
    private final Inventory<Item> inventory = new Inventory<>();

    private Map<String, Item> equipment = new HashMap<>();

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
        }
        else if (!Objects.isNull(nextCell.getGameObject()) && nextCell.getGameObject().isInteractive()) {
            return;
        }
        getCell().setActor(null);
        nextCell.setActor(this);
        setCell(nextCell);
    }
    public void pickUpItem() {
        if (isInventoryFull() || Objects.isNull(getCell().getItem())) {
            return;
        }
        addItemToInventory(getCell().getItem());
        getCell().getItem().removeItemFromMap();
    }

    public void useItem(int itemSlot) {
        if (inventory.getInventory().size() <= itemSlot ) {
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
