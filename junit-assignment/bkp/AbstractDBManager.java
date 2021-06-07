package com.ptc.assignment.junit.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractDBManager {
    private String dbURL;
    private String dbUsername;
    private String dbPassword;

    public AbstractDBManager() throws IOException {
        Properties properties = new Properties();
        properties.load(AbstractDBManager.class.getResourceAsStream("db.properties"));
        dbURL = properties.getProperty("db.url");
        dbUsername = properties.getProperty("db.username");
        dbPassword = properties.getProperty("db.password");
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    }
}
