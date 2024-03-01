package ru.andrey.caraccidentreport.util;

import ru.andrey.caraccidentreport.dto.LimitedAccidentDataDTO;
import ru.andrey.caraccidentreport.model.Address;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;

import java.sql.Timestamp;

public class LimitedAccidentDataConverter implements DTOMapper<LimitedAccidentDataDTO, LimitedAccidentData>{

    @Override
    public LimitedAccidentDataDTO convertToDTO(LimitedAccidentData data) {
        int guilt = data.getGuilt();
        Timestamp time = data.getTime();
        Address address = data.getAddress();
        return new LimitedAccidentDataDTO(address, guilt, time);
    }

    @Override
    public LimitedAccidentData convertFromDTO(LimitedAccidentDataDTO dto) {
        int guilt = dto.getGuilt();
        Timestamp time = dto.getTime();
        Address address = dto.getAddress();
        return new LimitedAccidentData(address, guilt, time);
    }
}
