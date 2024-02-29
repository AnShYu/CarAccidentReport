package ru.andrey.caraccidentreport.model;

public class AuthorizationData {

    private String login;
    private String password;

    public AuthorizationData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthorizationData(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
