package com.codecool.dungeoncrawl.itemtest;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class ItemTest {

    @BeforeEach
    public void setup() {
        Player player = new Player();
    }
}
