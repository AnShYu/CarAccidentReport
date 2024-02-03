package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.DriverProcessor;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.DriverData;
import ru.andrey.caraccidentreport.parsing.Driver;

public class PersonsServices {

    public long addPersonDataToDB(Driver driver) throws DataAccessException {

        String[] driverOneFullName = driver.getName().split(" ");
        String surnameOne = driverOneFullName[0];
        String firstNameOne = driverOneFullName[1];
        String fathersNameOne = driverOneFullName[2];
        String passportNumberOne = driver.getPassportNo();
//        String OSAGONumberOne = driver.getOSAGONumber();

        DriverData driverData = new DriverData();
        driverData.setFirstName(firstNameOne);
        driverData.setFathersName(fathersNameOne);
        driverData.setLastName(surnameOne);
        driverData.setPassport(passportNumberOne);

        DriverProcessor dp = new DriverProcessor();
        long driverID = dp.checkIfDriverIsInDB(driverData);
        if (driverID < 0) {
            driverID = dp.addDriver(driverData);
        }
        return driverID;


    }
}
