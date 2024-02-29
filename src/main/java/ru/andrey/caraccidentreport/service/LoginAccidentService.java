package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.LoginAccidentProcessor;
import ru.andrey.caraccidentreport.dbprocessing.NameAccidentProcessor;
import ru.andrey.caraccidentreport.dto.AuthorizationDataDTO;
import ru.andrey.caraccidentreport.dto.LimitedAccidentDataDTO;
import ru.andrey.caraccidentreport.model.LimitedAccidentData;

import java.util.ArrayList;
import java.util.List;

public class LoginAccidentService {

    public List<LimitedAccidentDataDTO> getAccidentDataByLogin(AuthorizationDataDTO authorizationDTO) {

        String login = authorizationDTO.getLogin();

        List<LimitedAccidentData> ladsList = new LoginAccidentProcessor().getAccidentDataByLogin(login);

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
