package ru.andrey.caraccidentreport;

import ru.andrey.caraccidentreport.htttpmanager.ClientRequestsManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ClientRequestsManager crm = new ClientRequestsManager();
        crm.startServer();
    }

}
