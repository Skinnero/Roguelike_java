package com.codecool.dungeoncrawl.Dao;

import com.codecool.dungeoncrawl.Model.PlayerModel;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    Dotenv dotenv = Dotenv.load();
    private final String DB_NAME = dotenv.get("DB_NAME");
    private final String DB_USER = dotenv.get("DB_USER");
    private final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    @SneakyThrows
    public void setup() {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(DB_NAME);
        dataSource.setUser(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}