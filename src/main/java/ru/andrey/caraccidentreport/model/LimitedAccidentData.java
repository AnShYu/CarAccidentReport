package ru.andrey.caraccidentreport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitedAccidentData {

    private Address address;
    private int guilt;
    private Timestamp time;


}

