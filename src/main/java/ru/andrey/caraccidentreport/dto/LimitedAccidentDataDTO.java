package ru.andrey.caraccidentreport.dto;

import ru.andrey.caraccidentreport.model.Address;
import java.sql.Timestamp;

public class LimitedAccidentDataDTO {

    private Address address;
    private int guilt;
    private Timestamp time;

    public LimitedAccidentDataDTO(Address address, int guilt, Timestamp time) {
        this.address = address;
        this.guilt = guilt;
        this.time = time;
    }

    public LimitedAccidentDataDTO() {
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
}
