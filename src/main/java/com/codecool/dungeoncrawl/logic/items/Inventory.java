package com.codecool.dungeoncrawl.logic.items;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T extends Item> {
    // It shouldn't be generic, should be a list<Item> because of it's lack of usage

    private final List<T> inventory = new ArrayList<>();

    public boolean isInventoryFull() {
        int INVENTORY_CAP = 10;
        return inventory.size() == INVENTORY_CAP;
    }

    public void addItem(T item) {
        inventory.add(item);
    }

    public Item getItem(int inventoryIndex) {
        return inventory.get(inventoryIndex);
    }

    public List<T> getInventory() {
        return inventory;
    }
}
