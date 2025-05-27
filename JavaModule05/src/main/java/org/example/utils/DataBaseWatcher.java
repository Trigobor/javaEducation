package org.website.utils;

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
    private static final String url = "jdbc:postgresql://localhost:5438/postgres";

    private DataBaseWatcher() throws SQLException {}

    public static DataBaseWatcher getWatcher() {
        if (watcher == null) {
            synchronized (DataBaseWatcher.class) {
                if (watcher == null) {
                    try {
                        watcher = new DataBaseWatcher();
                        auth.put("user", "user");
                        auth.put("password", "pass");
                        connection = DriverManager.getConnection(url, auth);
                        if (connection == null) {
                            System.err.println("Нет соединения с БД!");
                        } else {
                            System.out.println("Cоединения с БД установлено!");
                        }
                        //connection.setAutoCommit(true); оно и так по умолчанию true
                        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

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
    //сделать так чтобы пул подключений был ограничен и новые конешны не создавались каждый раз при обращении в метод ниже
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}

