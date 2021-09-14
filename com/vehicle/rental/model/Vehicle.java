package com.vehicle.rental.model;

public class Vehicle {
    private String licence;
    private VechicleType vechicleType;
    private int rentalPrice;

    @Override
    public String toString(){
        return licence + " " +vechicleType + " " + rentalPrice;
    }

    public Vehicle(String licence, VechicleType vechicleType, Integer price){
        setLicence(licence);
        setVechicleType(vechicleType);
        setRentalPrice(price);
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public VechicleType getVechicleType() {
        return vechicleType;
    }

    public void setVechicleType(VechicleType vechicleType) {
        this.vechicleType = vechicleType;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }
}
