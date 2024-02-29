package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.LoginProcessor;
import ru.andrey.caraccidentreport.dto.AuthorizationDataDTO;
import ru.andrey.caraccidentreport.model.AuthorizationData;

public class LoginService {

    public Boolean checkIfAuthorized(AuthorizationDataDTO lpDTO) {
        AuthorizationData lp = new AuthorizationData(lpDTO.getLogin(), lpDTO.getPassword());
        LoginProcessor loginProcessor = new LoginProcessor();
        return loginProcessor.checkLoginAndPasswordInDB(lp);
    }

}
