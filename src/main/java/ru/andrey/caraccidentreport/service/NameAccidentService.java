package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.FullNameProcessor;
import ru.andrey.caraccidentreport.dto.FullNameDTO;
import ru.andrey.caraccidentreport.model.AccidentCircumstances;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;

public class NameAccidentService {

    public String getAccidentDetails (FullNameDTO fullNameDTO) {

        String fullName = fullNameDTO.getFullName();


        String[] nameParts = fullName.split("_");
        String surname = nameParts[0];
        String name = nameParts[1];
        String fathersName = nameParts[2];

        LimitedAccidentData lad = new FullNameProcessor().getAccidentData(surname, name, fathersName);

        StringBuilder ladStringBuilder = new StringBuilder();
        ladStringBuilder = ladStringBuilder.append("address: " + lad.getAddress() + ", ");
        ladStringBuilder = ladStringBuilder.append("date: " + lad.getDate() + ", ");
        ladStringBuilder = ladStringBuilder.append("guilt: " + lad.getGuilt());

        String ladString = ladStringBuilder.toString();

        return ladString;



    }

}
