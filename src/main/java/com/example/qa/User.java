package com.example.qa;

public class User {

    private int id;
    private String name;
    private boolean active;

    public User (int id, String name, boolean active){
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public void setName(String name){
        this.name = name;
    }
}
