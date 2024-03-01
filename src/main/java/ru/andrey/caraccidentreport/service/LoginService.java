package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.LoginProcessor;
import ru.andrey.caraccidentreport.dto.AuthorizationDataDTO;
import ru.andrey.caraccidentreport.model.AuthorizationData;

public class LoginService {

    public Boolean checkIfAuthorized(String login, String password) {

        LoginProcessor loginProcessor = new LoginProcessor();

        return loginProcessor.checkLoginAndPasswordInDB(login, password);
    }

}
