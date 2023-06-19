package com.codecool.dungeoncrawl.Dao;

import com.codecool.dungeoncrawl.Model.GameState;

import java.util.List;

public interface GameStateDao {
    void add(GameState state);
    void update(GameState state);
    GameState get(int id);
    List<GameState> getAll();
}
