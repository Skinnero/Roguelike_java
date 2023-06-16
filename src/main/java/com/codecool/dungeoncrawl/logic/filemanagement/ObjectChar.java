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
    GATE('Y'),
    OGRE('O'),
    CHEST('c'),
    TORCH('T'),
    WATER('W'),
    BOAT('B'),
    GRASS('G'),
    LETTER_D('d'),
    LETTER_O('o'),
    LETTER_N('n'),
    LETTER_T('t'),
    LETTER_G('g'),
    LETTER_H('h'),
    LETTER_E('e'),
    LETTER_R('r'),
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
