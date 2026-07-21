package com.mycompany.prg381_project.model;

public class cleanerModel extends personModel {
    private String PhoneNumber;
    private String Name;
    private int id;
    
    public cleanerModel(){};
    
    @Override
    public String getRole(){
        return "Cleaner";
    }
    
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
    
    @Override
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    
    
}
