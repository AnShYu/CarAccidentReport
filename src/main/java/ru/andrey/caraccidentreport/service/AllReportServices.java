package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.AccidentProcessor;
import ru.andrey.caraccidentreport.dto.ReportTextDTO;
import ru.andrey.caraccidentreport.model.AccidentCircumstances;
import ru.andrey.caraccidentreport.model.CarData;
import ru.andrey.caraccidentreport.model.DriverData;
import ru.andrey.caraccidentreport.model.ReportText;
import ru.andrey.caraccidentreport.parsing.Accident;
import ru.andrey.caraccidentreport.parsing.Driver;
import ru.andrey.caraccidentreport.parsing.GeneralCircumstances;
import ru.andrey.caraccidentreport.dbprocessing.CarsProcessor;
import ru.andrey.caraccidentreport.dbprocessing.DriverProcessor;
import ru.andrey.caraccidentreport.dbprocessing.ReportTextProcessor;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.parsing.PatternsKeeper;
import ru.andrey.caraccidentreport.parsing.ReportParser;

public class AllReportServices {


    public void parceAndAddToDB(ReportTextDTO rtDTO) throws DataAccessException {

//      Parsing
        String report = rtDTO.getText();
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        Accident accident = ReportParser.makeParsing(report, patternsKeeper);

        Driver driverOne = accident.getReportingDriver();
        Driver driverTwo = accident.getSecondDriver();
        GeneralCircumstances generalCircumstances = accident.getCircumstances();


//      Requests to DB level
        AccidentServices accidentServices = new AccidentServices();
        long accidentId = accidentServices.addAccidentDataToDB(generalCircumstances);

        PersonsServices personsServices = new PersonsServices();
        long personIdOne = personsServices.addPersonDataToDB(driverOne);
        long personIdTwo = personsServices.addPersonDataToDB(driverTwo);

        CarsServices carsServices = new CarsServices();
        long carIdOne = carsServices.addCarDataToDB(driverOne, personIdOne);
        long carIdTwo = carsServices.addCarDataToDB(driverTwo, personIdTwo);

        AccidentParticipantServices aps = new AccidentParticipantServices();
        aps.addAccidentParticipantDataToDB(driverOne, accidentId, personIdOne, carIdOne);
        aps.addAccidentParticipantDataToDB(driverTwo, accidentId, personIdTwo, carIdTwo);

        ReportTextServices rts = new ReportTextServices();
        rts.addFullReportToDB(rtDTO);
    }
}
