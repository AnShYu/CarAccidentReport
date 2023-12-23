package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.FullNameProcessor;
import ru.andrey.caraccidentreport.dto.FullNameDTO;
import ru.andrey.caraccidentreport.model.AccidentCircumstances;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;

public class NameAccidentService {

    public LimitedAccidentData getAccidentDetails (FullNameDTO fullNameDTO) {

        String fullName = fullNameDTO.getFullName();


        String[] nameParts = fullName.split("_");
        String surname = nameParts[0];
        String name = nameParts[1];
        String fathersName = nameParts[2];

        LimitedAccidentData lad = new FullNameProcessor().getAccidentData(surname, name, fathersName);

        return lad;



    }

}
