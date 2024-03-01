package ru.andrey.caraccidentreport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.andrey.caraccidentreport.model.Address;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitedAccidentDataDTO {

    private Address address;
    private int guilt;
    private Timestamp time;

}
