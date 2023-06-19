package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;

import java.util.function.Consumer;

public enum CharOnMap {
    PLAYER('@', ActorSupplier::player),
    EMPTY(' ', EnvironmentalObjectSupplier::empty),
    WALL('#', EnvironmentalObjectSupplier::wall),
    FLOOR('.', EnvironmentalObjectSupplier::floor),
    SKELETON('s', ActorSupplier::skeleton),
    FOOD('a', ItemSupplier::food),
    KEY('k', ItemSupplier::key),
    SWORD('m', ItemSupplier::sword),
    ARMOR('b', ItemSupplier::armor),
    DOOR('Y', InteractiveObjectSupplier::door),
    OGRE('O', ActorSupplier::ogre),
    CHEST('c', InteractiveObjectSupplier::chest),
    TORCH('T', ItemSupplier::torch),
    WATER('W', EnvironmentalObjectSupplier::water),
    BOAT('B', InteractiveObjectSupplier::boat),
    GRASS('G', EnvironmentalObjectSupplier::grass),
    LETTER_D('d', EnvironmentalObjectSupplier::letterD),
    LETTER_O('o', EnvironmentalObjectSupplier::letterO),
    LETTER_N('n', EnvironmentalObjectSupplier::letterN),
    LETTER_T('t', EnvironmentalObjectSupplier::letterT),
    LETTER_G('g', EnvironmentalObjectSupplier::letterG),
    LETTER_H('h', EnvironmentalObjectSupplier::letterH),
    LETTER_E('e', EnvironmentalObjectSupplier::letterE),
    LETTER_R('r', EnvironmentalObjectSupplier::letterR),
    MAGE('M', ActorSupplier::mage);

    private final char charOnMapTxt;
    private final Consumer<Cell> strategy;

    CharOnMap(char charOnMapTxt, Consumer<Cell> strategy) {
        this.charOnMapTxt = charOnMapTxt;
        this.strategy = strategy;
    }

    public void apply(Cell cell) {
        strategy.accept(cell);
    }

    public static CharOnMap fromChar(char charOnMapTxt) {
        for (CharOnMap type : values()) {
            if (type.charOnMapTxt == charOnMapTxt) {
                return type;
            }
        }
        throw new RuntimeException("Unrecognized character: '" + charOnMapTxt + "'");
    }
}
