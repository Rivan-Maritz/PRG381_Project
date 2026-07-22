/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prg381_project.DAO;

import com.mycompany.prg381_project.Util.DBConnection;
import com.mycompany.prg381_project.model.stockissuanceModel;
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
public class stockissuanceDAO {
    public boolean CreateStockIssuance(stockissuanceModel stm)
    {
        String sql = "INSERT INTO StockIssuance(MaterialID,CleanerID,IssuedBy,Quantity,DateIssued,RemainingStock)"
                   + "VALUES (?,?,?,?,?,?)";
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, stm.getMaterialid());
            ps.setInt(2, stm.getCleanerid());
            ps.setInt(3, stm.getIssuedby());
            ps.setInt(4, stm.getQuantity());
            ps.setDate(5, stm.getDateIssued());
            ps.setInt(6, stm.getRemainingstock());
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
    public stockissuanceModel ReadStockIssuance(int id)
    {
        String sql = "SELECT * FROM StockIssuance WHERE IssuanceID = ?";
            try
            {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                
                try (ResultSet rs = ps.executeQuery()) 
                {
                if (rs.next()) {
                    return MapRowToStockIssuance(rs);
                }
                ps.close();
                con.close();
                
                
            }
                
            }catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
            return null;
    }
    public List<stockissuanceModel> ReadAllCleaners()
    {
        List<stockissuanceModel> StockIssuance = new ArrayList<>();
        String sql = "SELECT * FROM StockIssuance";
        try  {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                StockIssuance.add(MapRowToStockIssuance(rs));
            }
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return StockIssuance;
    }
        
    public boolean UpdateCleaner(stockissuanceModel stm)
    {
        String sql = "UPDATE StockIssuance SET MaterialID = ?, CleanerID = ?, IssuedBy = ?, Quantity = ?, RemainingStock = ?  WHERE IssuanceID = ?";
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, stm.getMaterialid());
            ps.setInt(2, stm.getCleanerid());
            ps.setInt(3, stm.getIssuedby());
            ps.setInt(4, stm.getQuantity());
            ps.setInt(5, stm.getIssuanceID());
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
    public boolean DeleteCleaner (int id)
        {
            String sql = "DELETE FROM StockIssuance WHERE IssuanceID = ?";
            try
            {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
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
    
    
    private stockissuanceModel MapRowToStockIssuance(ResultSet rs) throws SQLException
    {
        stockissuanceModel stm = new stockissuanceModel();
        stm.setIssuanceID(rs.getInt("IssuanceID"));
        stm.setMaterialid(rs.getInt("MaterialID"));
        stm.setCleanerid(rs.getInt("CleanerID"));
        stm.setIssuedby(rs.getInt("IssuedBy"));
        stm.setQuantity(rs.getInt("Quantity"));
        stm.setDateIssued(rs.getDate("DateIssued"));
        stm.setRemainingstock(rs.getInt("RemainingStock"));
        
        
        return stm;
    }
}
