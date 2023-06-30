package com.codecool.dungeoncrawl.fileloader;

import com.codecool.dungeoncrawl.fileloader.gamestateloader.GameStateLoader;
import com.codecool.dungeoncrawl.fileloader.gamestateloader.MonsterLoaderConsumer;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.ui.utils.TileType;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InteractiveObjectModel;
import com.codecool.dungeoncrawl.model.ItemModel;
import com.codecool.dungeoncrawl.model.MonsterModel;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapFileName) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFileName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, TileType.FLOOR);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(Position.of(x, y));
                    CharOnMap.fromChar(line.charAt(x)).apply(cell);
                    map.addToGameObjectList(cell);
                }
            }
        }
        return map;
    }

    public static GameMap loadAndPutObjectsOnMap(GameState gameState) {
        Player player = Player.getInstance();
        player.setPosition(Position.of(gameState.getPlayer().getPositionX(), gameState.getPlayer().getPositionY()));
        player.setHealth(gameState.getPlayer().getHealth());
        player.setName(gameState.getPlayer().getPlayerName());

        GameMap gameMap = MapLoader.loadMap(gameState.getCurrentMap());
        gameMap.setPlayer(player);

        for (MonsterModel monster : gameState.getMonsters()) {
            GameStateLoader.valueOf(monster.getName())
                    .apply(monster, gameMap.getCell(Position.of(monster.getPositionX(), monster.getPositionY())));
        }
        for (ItemModel item : gameState.getItems()) {
            GameStateLoader.valueOf(item.getName())
                    .apply(item, gameMap.getCell(Position.of(item.getPositionX(), item.getPositionY())));
        }
        for (InteractiveObjectModel interactiveObject : gameState.getInteractiveObjects()) {
            GameStateLoader.valueOf(interactiveObject.getName())
                    .apply(interactiveObject, gameMap.getCell(Position.of(interactiveObject.getPositionX(), interactiveObject.getPositionY())));
        }
        return gameMap;
    }

}
