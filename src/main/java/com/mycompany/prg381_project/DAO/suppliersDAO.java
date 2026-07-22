/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prg381_project.DAO;

import com.mycompany.prg381_project.Util.DBConnection;
import com.mycompany.prg381_project.model.suppliersModel;
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
public class suppliersDAO {
    public boolean CreateSuppliers(suppliersModel sm)
    {
        String sql = "INSERT INTO Suppliers(SupplierName,Contact,PhoneNumber,Email,Address)"
                   + "VALUES (?,?,?,?,?)";
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sm.getName());
            ps.setString(2, sm.getContact());
            ps.setString(3, sm.getPhoneNumber());
            ps.setString(4, sm.getEmail());
            ps.setString(5, sm.getAddress());
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
    public suppliersModel ReadSuppliers(int id)
    {
        String sql = "SELECT * FROM Suppliers WHERE SuppliersID = ?";
            try
            {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                
                try (ResultSet rs = ps.executeQuery()) 
                {
                if (rs.next()) {
                    return MapRowToSuppliers(rs);
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
    public List<suppliersModel> ReadSuppliers()
    {
        List<suppliersModel> Suppliers = new ArrayList<>();
        String sql = "SELECT * FROM Suppliers";
        try  {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                Suppliers.add(MapRowToSuppliers(rs));
            }
            con.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Suppliers;
    }
        
    public boolean UpdateSuppliers(suppliersModel sm)
    {
        String sql = "UPDATE Suppliers SET SupplierName = ?, Contact = ?, PhoneNumber = ?, Email = ?, Address = ?  WHERE SupplierID = ?";
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sm.getName());
            ps.setString(2, sm.getContact());
            ps.setString(3, sm.getPhoneNumber());
            ps.setString(4, sm.getEmail());
            ps.setString(5, sm.getAddress());
            ps.setInt(6, sm.getSupplierID());
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
    public boolean DeleteSuppliers (int id)
        {
            String sql = "DELETE FROM Suppliers WHERE SupplierID = ?";
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
    
    
    private suppliersModel MapRowToSuppliers(ResultSet rs) throws SQLException
    {
        suppliersModel sm = new suppliersModel();
        sm.setSupplierID(rs.getInt("SupplierID"));
        sm.setName(rs.getString("SupplierName"));
        sm.setContact(rs.getString("Contact"));
        sm.setPhoneNumber(rs.getString("PhoneNumber"));
        sm.setEmail(rs.getString("Email"));
        sm.setAddress(rs.getString("Address"));
        
        
        return sm;
    }

    public void setName(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setContact(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
