package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.AccidentProcessor;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.AccidentCircumstances;
import ru.andrey.caraccidentreport.parsing.GeneralCircumstances;

import java.sql.Timestamp;

public class AccidentServices {

    public long addAccidentDataToDB(GeneralCircumstances gc) throws DataAccessException {


        String[] address = gc.getAddress().split(" ");
        String city = address[0];
        String street = address[1] + " " + address[2];
        String building = address[3] + " " + address[4];
        String guilt = gc.getGuiltOfTheReportingDriver();
        boolean claimedGuilt = false;
        if(guilt.equals("признаю")) {
            claimedGuilt = true;
        }

        String[] date = gc.getDate().split("\\.");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);

        String[] time = gc.getTime().split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);

        long timeInNanoSeconds = (year - 1970)*365*24*60*60*1000000000 + month*29*24*60*60*1000000000 + day*24*60*60*1000000000 + hour*60*60*1000000000 + minute*60*1000000000;
        Timestamp timeStamp = new Timestamp(timeInNanoSeconds);
//        Timestamp timeStamp = new Timestamp(year, month, day, hour, minute, 0, 0);

//       Не поддерживаются поля таблицы (т.к. не распознаются парсером): область, район, driverDeclaredGuilty

        AccidentCircumstances circumstances = new AccidentCircumstances();
        circumstances.setCity(city);
        circumstances.setStreet(street);
        circumstances.setBuilding(building);
        circumstances.setDriverClaimedGuilt(claimedGuilt);
        circumstances.setDateAndTime(timeStamp);

        AccidentProcessor acpr = new AccidentProcessor();
        long accidentId = acpr.addAccident(circumstances);

        return accidentId;
    }
}
