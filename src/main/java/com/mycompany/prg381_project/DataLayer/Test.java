package com.mycompany.prg381_project.DataLayer;

import com.mycompany.prg381_project.DataLayer.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Connection successful!");
                System.out.println("Connected to: " + conn.getCatalog());
            }
        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }
}
