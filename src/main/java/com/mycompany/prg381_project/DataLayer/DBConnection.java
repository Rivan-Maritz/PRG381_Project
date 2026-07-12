package com.mycompany.prg381_project.DataLayer;

  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/cleaning_inventory_db";
    private static final String User = "postgres";
    private static final String Password = "jamesRamero_54@34";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, User, Password);
    }
}
