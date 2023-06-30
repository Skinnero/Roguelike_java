package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @SneakyThrows
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, x, y) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHealth());
            statement.setInt(3, player.getPositionX());
            statement.setInt(4, player.getPositionY());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        }
    }

    @SneakyThrows
    public int getRecentPlayerId() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "select id from player order by id desc limit 1";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    @Override
    public void update(PlayerModel player) {

    }

    @Override
    @SneakyThrows
    public PlayerModel get(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "select * from player where id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String playerName = resultSet.getString("player_name");
                int health = resultSet.getInt("hp");
                int posX = resultSet.getInt("x");
                int posY = resultSet.getInt("y");
                PlayerModel playerModel = new PlayerModel(playerName, health, posX, posY);
                System.out.println("Player id: " + id);
                System.out.println("Player name: " + playerName);
                return playerModel;
            }
            return null;
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }
}