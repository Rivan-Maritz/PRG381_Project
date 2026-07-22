package com.mycompany.prg381_project.model;

import java.sql.Date;

public class stockissuanceModel {
    private int IssuanceID;
    private int materialid;
    private int cleanerid;
    private int issuedby;
    private int quantity;
    private Date DateIssued;
    private int remainingstock;
    public stockissuanceModel(){}
    public stockissuanceModel(int IssuanceID, int materialid, int cleanerid, int issuedby, int quantity, Date DateIssued, int remainingstock) {
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

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
        this.materialid = materialid;
    }

    public int getCleanerid() {
        return cleanerid;
    }

    public void setCleanerid(int cleanerid) {
        this.cleanerid = cleanerid;
    }

    public int getIssuedby() {
        return issuedby;
    }

    public void setIssuedby(int issuedby) {
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
