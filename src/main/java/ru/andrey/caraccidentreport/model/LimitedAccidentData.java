package ru.andrey.caraccidentreport.model;

import java.sql.Timestamp;

public class LimitedAccidentData {

    private Address address;
    private int guilt;
    private Timestamp time;

    public LimitedAccidentData(Address address, int guilt, Timestamp time) {
        this.address = address;
        this.guilt = guilt;
        this.time = time;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getGuilt() {
        return guilt;
    }

    public void setGuilt(int guilt) {
        this.guilt = guilt;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "LimitedAccidentData{" +
                "address=" + address +
                ", guilt=" + guilt +
                ", time=" + time +
                '}';
    }
}

