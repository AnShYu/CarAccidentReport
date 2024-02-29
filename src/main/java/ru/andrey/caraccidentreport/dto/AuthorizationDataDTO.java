package ru.andrey.caraccidentreport.dto;

public class AuthorizationDataDTO {
    private String login;
    private String password;

    public AuthorizationDataDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthorizationDataDTO(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
