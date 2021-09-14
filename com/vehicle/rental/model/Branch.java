package com.vehicle.rental.model;

public class Branch {
    private String name;

    @Override
    public String toString(){
        return getName();
    }

    public Branch(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
