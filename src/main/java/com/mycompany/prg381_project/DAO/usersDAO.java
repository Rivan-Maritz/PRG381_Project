/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prg381_project.DAO;
import com.mycompany.prg381_project.Util.*;
import com.mycompany.prg381_project.model.usersModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author rivan
 */
public class usersDAO {
    public boolean RegisterUser(usersModel User)
    {
        String sql = "INSERT INTO Users(Username,Password,Email,Role)"
                   + "VALUES(?,?,?,?)";
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            String hashedPassword = PasswordUtil.hashPassword(User.getPassword());
            ps.setString(1, User.getUsername());
            ps.setString(2, hashedPassword);
            ps.setString(3, User.getEmail());
            ps.setString(4, User.getRole());
            int rows = ps.executeUpdate();
            ps.close();
            con.close();
            return rows>0;
            
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean LoginUser(String username, String plainPassword)
    {
          String sql = "SELECT Password FROM Users WHERE Username = ?";
          try
          {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("Password");
                    ps.close();
                    con.close();
                    return PasswordUtil.verifyPassword(plainPassword, storedHash);
                }
                ps.close();
                con.close();
            return false;
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean usernameExists(String username) 
    {
        String sql = "SELECT 1 FROM Users WHERE Username = ?";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) 
        {
        ex.printStackTrace();
        return true; 
        }
     
    }
    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM Users WHERE Email = ?";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) 
        {
            ex.printStackTrace();
            return true;
        }
    }

    // Returns the UserID for a given username, or -1 if not found.
    // Used after a successful login so the session can track who is
    // currently logged in (needed for StockIssuance.IssuedBy).
    public int getUserIdByUsername(String username) {
        String sql = "SELECT UserID FROM Users WHERE Username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("UserID");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
