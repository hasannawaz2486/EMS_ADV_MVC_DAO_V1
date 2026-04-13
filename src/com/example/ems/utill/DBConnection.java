package com.example.ems.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ems_v1";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty.12345";

    private DBConnection() {
    }

    //    public static Connection getConnection() throws SQLException {
    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found in project classpath.", e);
        }

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Database connected successfully.");
        return connection;
    }

}
//    }
