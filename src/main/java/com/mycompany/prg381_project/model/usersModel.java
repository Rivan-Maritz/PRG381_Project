package com.mycompany.prg381_project.model;

public class usersModel extends personModel{
    private int ID;
    private String username;
    private String password;
    private String email;
    private String role;
    public usersModel() {}
    public usersModel(int ID,String username, String password, String email, String role) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        
    }
    
    @Override
    public int getID() {
        return ID;
    }
    
    @Override
    public String getName(){
        return username;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
    
}
