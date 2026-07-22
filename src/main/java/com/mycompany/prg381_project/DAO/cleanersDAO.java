/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prg381_project.DAO;
import com.mycompany.prg381_project.model.cleanerModel;
import com.mycompany.prg381_project.Util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Tiisetso
 */
public class cleanersDAO {
    public boolean CreateCleaner(cleanerModel cl)
    {
        String sql = "INSERT INTO Cleaners(Name,PhoneNumber)"
                   + "VALUES (?,?)";
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cl.getName());
            ps.setString(2, cl.getPhoneNumber());
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
    public cleanerModel ReadCleanerByID(int id)
    {
        String sql = "SELECT * FROM Cleaners WHERE CleanerID = ?";
            try
            {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                
                try (ResultSet rs = ps.executeQuery()) 
                {
                if (rs.next()) {
                    return MapRowToCleaner(rs);
                }
                con.close();
                ps.close();
                
            }
                
            }catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
            return null;
    }
    public List<cleanerModel> ReadAllCleaners()
    {
        List<cleanerModel> cleaners = new ArrayList<>();
        String sql = "SELECT * FROM Cleaners";
        try  {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                cleaners.add(MapRowToCleaner(rs));
            }
            con.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cleaners;
    }
        
    public boolean UpdateCleaner(cleanerModel cl)
    {
        String sql = "UPDATE Cleaners SET Name = ?, PhoneNumber = ? WHERE CleanerID = ?";
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cl.getName());
            ps.setString(2, cl.getPhoneNumber());
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
    public boolean DeleteCleaner (int id)
        {
            String sql = "DELETE FROM Cleaners WHERE CleanerID = ?";
            try
            {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
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
    
    
    private cleanerModel MapRowToCleaner(ResultSet rs) throws SQLException
    {
        cleanerModel cl = new cleanerModel();
        cl.setName(rs.getString("Name"));
        cl.setId(rs.getInt("CleanerID"));
        cl.setPhoneNumber(rs.getString("PhoneNumber"));
        return cl;
    }
    
        
    
}
