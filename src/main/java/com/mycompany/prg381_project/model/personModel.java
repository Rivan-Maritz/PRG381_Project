package com.mycompany.prg381_project.model;

public abstract class personModel {
    public abstract int getID();
    public abstract String getName();
    public abstract String getRole();
    
    public String getDisplayInfo(){
        return String.format("[%s] ID: %d | Name %s",getRole(),getID(),getName());
    }
}
