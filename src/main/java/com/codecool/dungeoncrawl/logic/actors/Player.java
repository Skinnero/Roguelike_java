package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;
import com.codecool.dungeoncrawl.logic.items.Inventory;

import java.util.Objects;

public class Player extends Actor {
    private final Inventory<Item> equipment = new Inventory<>();

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
        } else if (!Objects.isNull(nextCell.getItem()) && !isInventoryFull()) {
            pickItem(nextCell);
        } else if (!Objects.isNull(nextCell.getGameObject()) && nextCell.getGameObject().isInteractive()) {
            return;
        }
        getCell().setActor(null);
        nextCell.setActor(this);
        setCell(nextCell);
    }

    public Inventory<Item> getEquipment() {
        return equipment;
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
        equipment.addItem(item);
    }

    private boolean isInventoryFull() {
        return equipment.isInventoryFull();
    }

    private void pickItem(Cell cell) {
        addItemToInventory(cell.getItem());
        cell.setItem(null);
    }

    private void makeAttack(Cell cell) {
        cell.getActor().setHealth(cell.getActor().getHealth() - getAttack());
    }
}
