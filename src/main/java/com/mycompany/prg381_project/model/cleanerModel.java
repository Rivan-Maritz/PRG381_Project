package com.mycompany.prg381_project.model;

public class cleanerModel {
    private String id;
    private String Name;

    public cleanerModel(String id, String Name) {
        this.id = id;
        this.Name = Name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    
}
