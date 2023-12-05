package ru.andrey.caraccidentreport.model;

import java.sql.Timestamp;

public class AccidentCircumstances {
    // Указываю все поля, которые есть в таблице Accident
    private String province;
    private String city;
    private String district;
    private String street;
    private String building;
    private boolean DriverClaimedGuilt;
    private boolean DriverDeclaredGuilty;
    private Timestamp dateAndTime;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public boolean isDriverClaimedGuilt() {
        return DriverClaimedGuilt;
    }

    public void setDriverClaimedGuilt(boolean driverClaimedGuilt) {
        DriverClaimedGuilt = driverClaimedGuilt;
    }

    public boolean isDriverDeclaredGuilty() {
        return DriverDeclaredGuilty;
    }

    public void setDriverDeclaredGuilty(boolean driverDeclaredGuilty) {
        DriverDeclaredGuilty = driverDeclaredGuilty;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
