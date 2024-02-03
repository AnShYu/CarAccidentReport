package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.AccidentParticipantProcessor;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.AccidentParticipantData;
import ru.andrey.caraccidentreport.parsing.Driver;

public class AccidentParticipantServices {
    public void addAccidentParticipantDataToDB(Driver driver, long accidentID, long driverID, long carID) throws DataAccessException {

        String OSAGO = driver.getOSAGONumber();
        int aID = (int) accidentID;
        int dID = (int) driverID;
        int cID = (int) carID;


        AccidentParticipantData apd = new AccidentParticipantData();
        apd.setOSAGO(OSAGO);
        apd.setAccidentID(aID);
        apd.setDriverID(dID);
        apd.setCarID(cID);


        AccidentParticipantProcessor app = new AccidentParticipantProcessor();
        app.addAccidentParticipant(apd);
    }
}
