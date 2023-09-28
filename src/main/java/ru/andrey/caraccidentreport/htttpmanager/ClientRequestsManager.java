package ru.andrey.caraccidentreport.htttpmanager;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ClientRequestsManager {

    public void startServer () throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/accidentreport/cars", new CarsHandler());
        server.start();
    }

}
