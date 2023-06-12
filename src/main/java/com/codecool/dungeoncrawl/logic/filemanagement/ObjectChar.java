package com.codecool.dungeoncrawl.logic.filemanagement;

public enum ObjectChar {
    VOID(' '),
    WALL('#'),
    FLOOR('.'),
    SKELETON('s'),
    PLAYER('@'),
    FOOD('a'),
    KEY('k'),
    SWORD('m'),
    ARMOR('b'),
    GATE('g'),
    OGRE('O'),
    MAGE('M');

    private final char ch;

    ObjectChar(char ch) {
        this.ch = ch;
    }

    public static ObjectChar fromChar(char ch) {
        for (ObjectChar type : values()) {
            if (type.ch == ch) {
                return type;
            }
        }
        throw new RuntimeException("Unrecognized character: '" + ch + "'");
    }
}
