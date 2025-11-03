package com.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {
	public static Connection connectAuction() {
		Connection conn = null;
        try {
        	
            Class.forName("org.sqlite.JDBC");
            //Get the path of the database relative to the project
            String dbPath = System.getProperty("user.dir") + "/Databases/AuctionDB.db";
            String url = "jdbc:sqlite:" + dbPath;

            // Establish the connection
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        return conn;
	}
	
	public static Connection connectUsers() {
		Connection conn = null;
        try {
        	
            Class.forName("org.sqlite.JDBC");
            //Get the path of the database relative to the project
            String dbPath = System.getProperty("user.dir") + "/Databases/Users.db";
            String url = "jdbc:sqlite:" + dbPath;

            // Establish the connection
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        return conn;
	}
}