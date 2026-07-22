package com.mycompany.prg381_project.model;


import java.sql.Date;

public class materialsModel {
    private int materialID;
    private int supplierid;
    private String MName;
    private String type;
    private int stock;
    private Date dateAdded;
    private int reorderLevel;
    public materialsModel(){}
    public materialsModel(int materialID,int supplierid, String MName, String type, int stock, Date dateAdded,int reorderLevel) {
        this.materialID = materialID;
        this.supplierid = supplierid;
        this.MName = MName;
        this.type = type;
        this.stock = stock;
        this.dateAdded = dateAdded;
        this.reorderLevel = reorderLevel;
        
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getMName() {
        return MName;
    }

    public void setMName(String MName) {
        this.MName = MName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    
}
