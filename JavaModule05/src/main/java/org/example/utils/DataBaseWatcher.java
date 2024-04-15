package org.example.utils;

import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseWatcher {
    private static EntityManagerFactory entityManagerFactory;
    private static Connection connection;
    private static DataBaseWatcher watcher;
    private static final Properties auth = new Properties();
    private static final String forName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5450/postgres";
    private DataBaseWatcher() throws SQLException {
        auth.put("user", "user");
        auth.put("password", "pass");
        connection = DriverManager.getConnection(url, auth);
        if (connection == null)
            System.err.println("Нет соединения с БД!");
        else
            System.out.println("Cоединения с БД установлено!");
        connection.setAutoCommit(true);
        entityManagerFactory = Persistence.createEntityManagerFactory("SomePersistenceUserName");
    }
    static DataBaseWatcher getWatcher() throws SQLException {
        if (watcher == null) {
            synchronized (DataBaseWatcher.class) {
                if (watcher == null) {
                    try {
                    watcher = new DataBaseWatcher();
                    } catch (Exception e) {
                        System.out.println("sukablyad!" + e);
                    }
                }
            }
        }
        return watcher;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}

