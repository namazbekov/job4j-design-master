package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = null;
        Config config = new Config("./data/app.properties");
        Map<String, String> map = config.load();
        for (Map.Entry<String, String> map1 : map.entrySet()) {
            Class.forName(""+ map1.getKey());
            url = "" + map1.getValue();
        }
        String login = "postgres";
        String password = "password";
        if (url != null) {
            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getUserName());
                System.out.println(metaData.getURL());
            }
        }
    }
}