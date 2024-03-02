package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.AccidentDataProvider;
import ru.andrey.caraccidentreport.dbprocessing.AccidentByLoginProcessor;
import ru.andrey.caraccidentreport.dto.LimitedAccidentDataDTO;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;
import ru.andrey.caraccidentreport.util.DTOMapper;
import ru.andrey.caraccidentreport.util.LimitedAccidentDataConverter;

import java.util.ArrayList;
import java.util.List;

public class LoginAccidentService {

    public List<LimitedAccidentDataDTO> getAccidentDataByLogin(String login) {

        AccidentDataProvider<String, List<LimitedAccidentData>> accidentDataProvider = new AccidentByLoginProcessor();

        List<LimitedAccidentData> ladsList = accidentDataProvider.getAccidentData(login);

        List<LimitedAccidentDataDTO> ladDTOsList = new ArrayList<>();
        DTOMapper<LimitedAccidentDataDTO, LimitedAccidentData> mapper = new LimitedAccidentDataConverter();
        for (LimitedAccidentData lad: ladsList) {
            LimitedAccidentDataDTO ladDTO = mapper.convertToDTO(lad);
            ladDTOsList.add(ladDTO);
        }
        return ladDTOsList;
    }

}
