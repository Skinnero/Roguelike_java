package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public abstract class InteractiveObject {
    private final Cell cell;
    private boolean interactive = true;

    public InteractiveObject(Cell cell) {
        this.cell = cell;
    }

    public void onInteraction(Player player) {
    }


    public boolean isInteractive() {
        return interactive;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
}
