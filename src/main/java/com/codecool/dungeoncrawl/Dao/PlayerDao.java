package com.codecool.dungeoncrawl.Dao;

import com.codecool.dungeoncrawl.Model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(PlayerModel player);
    void update(PlayerModel player);
    PlayerModel get(int id);
    List<PlayerModel> getAll();
}
