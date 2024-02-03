package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.CarsProcessor;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.CarData;
import ru.andrey.caraccidentreport.parsing.Driver;

public class CarsServices {

    public long addCarDataToDB(Driver driver, long driverId) throws DataAccessException {

        String[] carOneBrandandModel = driver.getCarBrandAndModel().split(" ");
        String carOneBrand = carOneBrandandModel[0];
        String carOneModel = carOneBrandandModel[1];
        String carOnePlate = driver.getCarPlateNumber();

        CarData car = new CarData();
        car.setCarBrand(carOneBrand);
        car.setCarModel(carOneModel);
        car.setCarPlate(carOnePlate);

        CarsProcessor cp = new CarsProcessor();
        long carID = cp.checkIfCarIsInDB(car);
        if (carID < 0) {
            carID = cp.addCar(car, driverId);
        }
        return carID;

    }

}
