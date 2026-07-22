/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prg381_project.DAO;
import com.mycompany.prg381_project.model.materialsModel;
import com.mycompany.prg381_project.Util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rivan
 */
public class materialsDAO {
    public boolean CreateMetrial(materialsModel mm)
    {
        String sql = 
                "INSERT INTO Materials(SupplierID,MaterialName,Type,Stock,ReorderLevel,DateAdded)"
               + "VALUES(?,?,?,?,?,?)";
        try
        {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mm.getSupplierid());
            ps.setString(2, mm.getMName());
            ps.setString(3,mm.getType());
            ps.setInt(4, mm.getStock());
            ps.setInt(5, mm.getReorderLevel());
            ps.setDate(6, mm.getDateAdded());
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
        public materialsModel ReadMetrialByID(int materialID)
        {
            String sql = "SELECT * FROM Materials WHERE MaterialID = ?";
            try(Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql))
            {
                
                ps.setInt(1, materialID);
                
                try (ResultSet rs = ps.executeQuery()) 
                {
                if (rs.next()) {
                    return mapRowToMaterial(rs);
                }
                
                
            }
                
            }catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
            return null;
        }
        
        public List<materialsModel> ReadAllMaterials() 
        {
        List<materialsModel> materials = new ArrayList<>();
        String sql = "SELECT * FROM Materials ORDER BY MaterialID";
 
        try(Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql))  
        {
            
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                materials.add(mapRowToMaterial(rs));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materials;
        }
        
        public boolean UpdateMaterials(materialsModel mm)
        {
            String sql = "UPDATE Materials SET "
                       + "SupplierID = ?,MaterialName = ?,Type = ?,Stock = ? ,ReorderLevel = ? "
                       + "WHERE MaterialID = ? ";
            try
            {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, mm.getSupplierid());
                ps.setString(2, mm.getMName());
                ps.setString(3, mm.getType());
                ps.setInt(4, mm.getStock());
                ps.setInt(5, mm.getReorderLevel());
                ps.setInt(6, mm.getMaterialID());
                
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
        public boolean DeleteMaterials (int materialID)
        {
            String sql = "DELETE FROM Materials WHERE MaterialID = ?";
            try
            {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, materialID);
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
        public List<materialsModel> getLowStockMaterials() 
        {
            List<materialsModel> lowStock = new ArrayList<>();
            String sql = "SELECT * FROM Materials WHERE Stock <= ReorderLevel ORDER BY Stock";
            try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
                while (rs.next()) 
                {
                lowStock.add(mapRowToMaterial(rs));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return lowStock;
        }
        
        public List<materialsModel> searchMaterialsByName(String keyword) 
        {
            List<materialsModel> results = new ArrayList<>();
            String sql = "SELECT * FROM Materials WHERE MaterialName ILIKE ? ORDER BY MaterialName";
            try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, "%" + keyword + "%");
                try (ResultSet rs = ps.executeQuery()) 
                {
                    while (rs.next()) 
                    {
                        results.add(mapRowToMaterial(rs));
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return results;
        }
        //mapping
        private materialsModel mapRowToMaterial(ResultSet rs) throws SQLException {
        materialsModel m = new materialsModel();
        m.setMaterialID(rs.getInt("MaterialID"));
        m.setSupplierid(rs.getInt("SupplierID"));
        m.setMName(rs.getString("MaterialName"));
        m.setType(rs.getString("Type"));
        m.setStock(rs.getInt("Stock"));
        m.setDateAdded(rs.getDate("DateAdded"));
        m.setReorderLevel(rs.getInt("ReorderLevel"));
        return m;
    }
    }

