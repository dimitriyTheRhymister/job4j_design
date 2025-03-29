package ru.job4j.jdbc;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    public TableEditor() {
        initConnection();
    }

    private void initConnection() {
        try {
            connection = createConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (in == null) {
                throw new FileNotFoundException("Property file 'app.properties' not found in the classpath");
            }
            config.load(in);
        }
        String url = config.getProperty("url");
        String driver = config.getProperty("driver_class");
        String username = config.getProperty("username");
        String password = config.getProperty("password");
        Class.forName(driver);

        return DriverManager.getConnection(url, username, password);
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS " + tableName + "(%s, %s);",
                    "id SERIAL PRIMARY KEY",
                    "name TEXT"
            );
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS " + tableName
            );
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE "
                            + tableName
                            + " ADD "
                            + columnName
                            + " "
                            + type
            );
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE "
                            + tableName
                            + " DROP COLUMN "
                            + columnName
            );
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE "
                            + tableName
                            + " RENAME COLUMN "
                            + columnName
                            + " TO "
                            + newColumnName
            );
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
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
        try (TableEditor tableEditor = new TableEditor()) {
            tableEditor.createTable("new_table");
            String tableScheme = tableEditor.getTableScheme("new_table");
            System.out.println(tableScheme);
            tableEditor.addColumn("new_table", "new_column", "int");

            tableEditor.renameColumn("new_table", "new_column", "new_column2");

            tableEditor.dropColumn("new_table", "new_column2");

            tableEditor.dropTable("new_table");
        }
    }
}