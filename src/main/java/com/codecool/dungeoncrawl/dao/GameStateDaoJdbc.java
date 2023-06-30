package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Data
@AllArgsConstructor
public class GameStateDaoJdbc implements GameStateDao {

    private DataSource dataSource;

    @Override
    @SneakyThrows
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id) values (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setTimestamp(2, new Timestamp(state.getSavedAt().getTime()));
            statement.setInt(3, state.getPlayer().getId());

            statement.executeUpdate();

//            ResultSet genKeys = statement.getGeneratedKeys();
//            if (genKeys.next()) {
//                state.setId(genKeys.getInt(1));
//            }
        }
    }

    @Override
    public void update(GameState state) {

    }

    @Override
    @SneakyThrows
    public GameState get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM game_state WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
//                int gameStateId = resultSet.getInt("id");
//                int playerId = resultSet.getInt("player_id");
//                String currentMap = resultSet.getString("current_map");
//                System.out.println("Current map: " + currentMap);
//                System.out.println("Player Id: " + playerId);
                return new GameState(resultSet.getString("current_map"));
            }
        }
        return null;
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
