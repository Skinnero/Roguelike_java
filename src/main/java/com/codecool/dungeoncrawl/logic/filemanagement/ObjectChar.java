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
    GATE('g'),
    OGRE('O'),
    MAGE('M');

    private final char[] chars;

    ObjectChar(char... chars) {
        this.chars = chars;
    }

    public static ObjectChar fromChar(char c) {
        for (ObjectChar ch : values()) {
            for (char charac : ch.chars) {
                if (charac == c) {
                    return ch;
                }
            }
        }
        throw new RuntimeException("Unrecognized character: '" + c + "'");
    }
}
