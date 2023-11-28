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

public class ReportTextService {


    public void parceAndAddToDB(ReportTextDTO rtDTO) throws DataAccessException {
        String report = rtDTO.getText();
        PatternsKeeper patternsKeeper = new PatternsKeeper();
        Accident accident = ReportParser.makeParsing(report, patternsKeeper);

        Driver driverOne = accident.getReportingDriver();
        Driver driverTwo = accident.getSecondDriver();
        GeneralCircumstances generalCircumstances = accident.getCircumstances();

        // Создаем объекты-контейнеры, которые будут передаваться на уровень БД
        DriverData driverOneData = new DriverData();
        CarData carOne = new CarData();
        DriverData driverTwoData = new DriverData();
        CarData carTwo = new CarData();
        AccidentCircumstances circumstances = new AccidentCircumstances();




        String[] address = generalCircumstances.getAddress().split(" ");
        String city = address[0];
        String street = address[1] + address[2];
        String building = address[3] + address[4];
// В таблице нет поля дат
//       String date = generalCircumstances.getDate();
        String guilt = generalCircumstances.getGuiltOfTheReportingDriver();
        boolean claimedGuilt = false;
        if(guilt.equals("признаю")) {
            claimedGuilt = true;
        }
        String time = generalCircumstances.getTime();
        // Не поддерживаются поля таблицы (т.к. не распознаются парсером): область, район, driverDeclaredGuilty
        circumstances.setCity(city);
        circumstances.setStreet(street);
        circumstances.setBuilding(building);
        circumstances.setDriverClaimedGuilt(claimedGuilt);
        circumstances.setTime(time);



        String[] driverOneFullName = driverOne.getName().split(" ");
        String surnameOne = driverOneFullName[0];
        String firstNameOne = driverOneFullName[1];
        String fathersNameOne = driverOneFullName[2];
        String passportNumberOne = driverOne.getPassportNo();
        String OSAGONumberOne = driverOne.getOSAGONumber();

        driverOneData.setFirstName(firstNameOne);
        driverOneData.setFathersName(fathersNameOne);
        driverOneData.setLastName(surnameOne);
        driverOneData.setPassport(passportNumberOne);
        driverOneData.setOSAGONumber(OSAGONumberOne);

        String[] carOneBrandandModel = driverOne.getCarBrandAndModel().split(" ");
        String carOneBrand = carOneBrandandModel[0];
        String carOneModel = carOneBrandandModel[1];
        String carOnePlate = driverOne.getCarPlateNumber();

        carOne.setCarBrand(carOneBrand);
        carOne.setCarModel(carOneModel);
        carOne.setCarPlate(carOnePlate);




        String[] driverTwoFullName = driverTwo.getName().split(" ");
        String surnameTwo = driverTwoFullName[0];
        String firstNameTwo = driverTwoFullName[1];
        String fathersNameTwo = driverTwoFullName[2];
        String passportNumberTwo = driverTwo.getPassportNo();
        String OSAGONumberTwo = driverTwo.getOSAGONumber();

        driverTwoData.setFirstName(firstNameTwo);
        driverTwoData.setFathersName(fathersNameTwo);
        driverTwoData.setLastName(surnameTwo);
        driverTwoData.setPassport(passportNumberTwo);
        driverTwoData.setOSAGONumber(OSAGONumberTwo);



        String[] carTwoBrandandModel = driverTwo.getCarBrandAndModel().split(" ");
        String carTwoBrand = carTwoBrandandModel[0];
        String carTwoModel = carTwoBrandandModel[1];
        String carTwoPlate = driverTwo.getCarPlateNumber();

        carTwo.setCarBrand(carTwoBrand);
        carTwo.setCarModel(carTwoModel);
        carTwo.setCarPlate(carTwoPlate);


        AccidentProcessor acpr = new AccidentProcessor();
        long accidentId = acpr.addAccident(circumstances);




        DriverProcessor dp = new DriverProcessor();
        dp.addDriver(driverOneData);
        dp.addDriver(driverTwoData);


        CarsProcessor cp = new CarsProcessor();
        cp.addACar(carOne);
        cp.addACar(carTwo);
        // Код для обращения к методу добавления в базу


    }



    public void addFullReportToDB(ReportTextDTO rtDTO) throws DataAccessException {
        String report = rtDTO.getText();

        ReportText rt = new ReportText();
        rt.setReportText(report);

        ReportTextProcessor rtp = new ReportTextProcessor();
        rtp.addReportText(rt);
    }

}
