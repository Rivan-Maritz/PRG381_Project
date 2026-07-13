package com.mycompany.prg381_project.model;

import java.time.LocalDate;

public class stockissuanceModel {
    private String materialid;
    private String cleanerid;
    private String issuedby;
    private int quantity;
    private LocalDate DateIssued;
    private int remainingstock;

    public stockissuanceModel(String materialid, String cleanerid, String issuedby, int quantity, LocalDate DateIssued, int remainingstock) {
        this.materialid = materialid;
        this.cleanerid = cleanerid;
        this.issuedby = issuedby;
        this.quantity = quantity;
        this.DateIssued = DateIssued;
        this.remainingstock = remainingstock;
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

    public LocalDate getDateIssued() {
        return DateIssued;
    }

    public void setDateIssued(LocalDate DateIssued) {
        this.DateIssued = DateIssued;
    }

    public int getRemainingstock() {
        return remainingstock;
    }

    public void setRemainingstock(int remainingstock) {
        this.remainingstock = remainingstock;
    }
    
    
}
