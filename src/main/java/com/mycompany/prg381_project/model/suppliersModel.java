package com.mycompany.prg381_project.model;

public class suppliersModel extends personModel{
    private int SupplierID;
    private String Name;
    private String Contact;
    private String PhoneNumber;
    private String Email;
    private String Address;
    public suppliersModel(){}
    
    @Override
    public int getID() {
        return SupplierID;
    }
    
    @Override
    public String getRole(){
        return "Supplier";
    }
    
    public suppliersModel(int SupplierID, String Name, String Contact, String PhoneNumber, String Email, String Address) {
        this.SupplierID = SupplierID;
        this.Name = Name;
        this.Contact = Contact;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Address = Address;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }
    
    @Override
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
   
}
