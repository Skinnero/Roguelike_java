package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;
import com.codecool.dungeoncrawl.logic.items.Inventory;

import java.util.Objects;

public class Player extends Actor {
    private final Inventory<Item> equipment = new Inventory<>();

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
        if (equipment.getInventory().size() <= itemSlot ) {
            return;
        }
        equipment.getItem(itemSlot).onUse(this);
        equipment.getInventory().remove(itemSlot);
    }

    public Inventory<Item> getEquipment() {
        return equipment;
    }

    private void addItemToInventory(Item item) {
        equipment.addItem(item);
    }

    private boolean isInventoryFull() {
        return equipment.isInventoryFull();
    }

    private void makeAttack(Cell cell) {
        cell.getActor().setHealth(cell.getActor().getHealth() - getAttack());
    }
}
