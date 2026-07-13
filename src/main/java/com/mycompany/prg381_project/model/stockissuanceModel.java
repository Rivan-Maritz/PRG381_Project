package com.mycompany.prg381_project.model;

import java.sql.Date;

public class stockissuanceModel {
    private int IssuanceID;
    private String materialid;
    private String cleanerid;
    private String issuedby;
    private int quantity;
    private Date DateIssued;
    private int remainingstock;
    public stockissuanceModel(){}
    public stockissuanceModel(int IssuanceID, String materialid, String cleanerid, String issuedby, int quantity, Date DateIssued, int remainingstock) {
        this.IssuanceID = IssuanceID;
        this.materialid = materialid;
        this.cleanerid = cleanerid;
        this.issuedby = issuedby;
        this.quantity = quantity;
        this.DateIssued = DateIssued;
        this.remainingstock = remainingstock;
    }

    public int getIssuanceID() {
        return IssuanceID;
    }

    public void setIssuanceID(int IssuanceID) {
        this.IssuanceID = IssuanceID;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getCleanerid() {
        return cleanerid;
    }

    public void setCleanerid(String cleanerid) {
        this.cleanerid = cleanerid;
    }

    public String getIssuedby() {
        return issuedby;
    }

    public void setIssuedby(String issuedby) {
        this.issuedby = issuedby;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateIssued() {
        return DateIssued;
    }

    public void setDateIssued(Date DateIssued) {
        this.DateIssued = DateIssued;
    }

    public int getRemainingstock() {
        return remainingstock;
    }

    public void setRemainingstock(int remainingstock) {
        this.remainingstock = remainingstock;
    }

    
    
    
}
