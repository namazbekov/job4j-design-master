package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private Statement statement = null;

    private static Properties properties;

    public TableEditor(Properties properties) throws SQLException {
        TableEditor.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException {
        connection = DriverManager.getConnection(
                properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        statement = connection.createStatement();
    }

    public void createTable(String tableName) throws  Exception {
        String sql = String.format(
                "create table " + tableName + "(%s, %s);",
                "id serial primary key",
                "name text"
        );
        statement.execute(sql);
        System.out.println("Create table successfully");
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = "DROP TABLE " + tableName;
        statement.execute(sql);
        System.out.println("Drop table successfully");
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String query = "ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type;
        statement.execute(query);
        System.out.println("Add column successfully");
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String query = "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
        statement.execute(query);
        System.out.println("Drop column successfully");
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String query = "ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName;
        statement.execute(query);
        System.out.println("Rename column successfully");
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }


    public static void main(String[] args) throws Exception {
        properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("properties.properties")) {
            properties.load(in);
        }
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable(" animal");
        System.out.println(getTableScheme(connection, "animal"));
        tableEditor.addColumn("animal", "nameAnimal", "text");
        System.out.println(getTableScheme(connection, "animal"));
        tableEditor.renameColumn(
                "animal", " nameAnimal", "ageAnimal");
        System.out.println(getTableScheme(connection, "animal"));
        tableEditor.dropColumn("animal", "ageAnimal");
        System.out.println(getTableScheme(connection, "animal"));
        tableEditor.dropTable("animal");
    }
}