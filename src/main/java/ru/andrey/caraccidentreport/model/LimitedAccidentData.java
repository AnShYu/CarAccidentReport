package ru.andrey.caraccidentreport.model;

public class LimitedAccidentData {

    private String address;
    private String date;
    private String guilt;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGuilt() {
        return guilt;
    }

    public void setGuilt(String guilt) {
        this.guilt = guilt;
    }
}
