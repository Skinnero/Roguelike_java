package com.codecool.dungeoncrawl.logic.gameobject;

import com.codecool.dungeoncrawl.logic.ui.Cell;
import com.codecool.dungeoncrawl.logic.ui.Drawable;

public abstract class GameObject implements Drawable {
    private final Cell cell;
    private boolean interactive;

    public GameObject(Cell cell) {
        this.cell = cell;
        this.cell.setGameObject(this);
    }

    public void onInteraction() {

    }

    public Cell getCell() {
        return cell;
    }

    public boolean isInteractive() {
        return interactive;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
}
