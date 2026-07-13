package com.mycompany.prg381_project.model;

public class cleanerModel {
    private String PhoneNumber;
    private String Name;
    private int id;
    
    public cleanerModel(){};
    public cleanerModel(String PhoneNumber, String Name, int id) {
        this.PhoneNumber = PhoneNumber;
        this.Name = Name;
        this.id = id;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    
    
}
