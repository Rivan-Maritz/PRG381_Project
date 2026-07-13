/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prg381_project.DAO;
import com.mycompany.prg381_project.Util.DBConnection;
import com.mycompany.prg381_project.model.usersModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            ps.setString(1, User.getUsername());
            ps.setString(2, User.getPassword());
            ps.setString(3, User.getEmail());
            ps.setString(4, User.getRole());
            int rows = ps.executeUpdate();
            con.close();
            ps.close();
            return rows>0;
            
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean LoginUser(String Username, String Password)
    {
          String sql = "SELECT Password FROM Users WHERE Username = ?";
          try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Username);
            ps.setString(1, Password);
             try (ResultSet rs = ps.executeQuery()) {
                
                return rs.next();
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }

}
