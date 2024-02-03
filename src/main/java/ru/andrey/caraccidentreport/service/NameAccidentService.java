package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.NameAccidentProcessor;
import ru.andrey.caraccidentreport.dto.FullNameDTO;
import ru.andrey.caraccidentreport.dto.LimitedAccidentDataDTO;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;
import java.util.ArrayList;
import java.util.List;

public class NameAccidentService {

    public List<LimitedAccidentDataDTO> getAccidentDetails (FullNameDTO fullNameDTO) {

        String fullName = fullNameDTO.getFullName();


        String[] nameParts = fullName.split("_");
        String surname = nameParts[0];
        String name = nameParts[1];
        String fathersName = nameParts[2];

        List<LimitedAccidentData> ladsList = new NameAccidentProcessor().getAccidentData(surname, name, fathersName);

        List<LimitedAccidentDataDTO> ladDTOsList = new ArrayList<>();
        for(LimitedAccidentData lad: ladsList) {
            LimitedAccidentDataDTO ladDTO = new LimitedAccidentDataDTO();
            ladDTO.setAddress(lad.getAddress());
            ladDTO.setTime(lad.getTime());
            ladDTO.setGuilt(lad.getGuilt());
            ladDTOsList.add(ladDTO);
        }

        return ladDTOsList;



    }

}
