package com.codecool.dungeoncrawl.itemtest;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Armor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArmorTest extends ItemTest{

    @Test
    public void testOnUseShouldRemoveFromInventory() {
        // arrange
        Armor armor = new Armor();
        Player player = new Player();
        player.addToInventory(armor);

        // act
        armor.onUse(player);

        // assert
        assertEquals(player.getInventory().size(), 0);
    }

}
