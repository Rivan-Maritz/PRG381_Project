package com.mycompany.prg381_project;

  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.SQLException;

public class PRG381_Project {

    String url = "jdbc:postgresql://localhost:5432/cleaning_inventory_db";
    String user = "cleaninv_user";
    String password = "yourpassword";

    public void testConnection() {
        try (Connection _ = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected successfully!");
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        PRG381_Project app = new PRG381_Project();
        app.testConnection();
    }
}