package ru.andrey.caraccidentreport.service;

import ru.andrey.caraccidentreport.dbprocessing.CarsProcessor;
import ru.andrey.caraccidentreport.exceptions.DataAccessException;
import ru.andrey.caraccidentreport.model.CarData;
import ru.andrey.caraccidentreport.dto.CarDTO;

import java.util.ArrayList;
import java.util.List;

public class ServiceForCars {

    private CarDTO convertToCarDto(CarData car) {
            CarDTO carDto = new CarDTO();
            carDto.setCarBrand(car.getCarBrand());
            carDto.setCarModel(car.getCarModel());
            carDto.setCarPlate(car.getCarPlate());
            return carDto;
    }

    private CarData convertToCar(CarDTO carDto) {
        CarData car = new CarData();
        car.setCarBrand(carDto.getCarBrand());
        car.setCarModel(carDto.getCarModel());
        car.setCarPlate(carDto.getCarPlate());
        return car;
    }

    public List<CarDTO> makeListOfCarDTOs(List<CarData> cars) {
        List<CarDTO> carDtos = new ArrayList<>();
        for (CarData car: cars) {
            CarDTO carDto = convertToCarDto(car);
            carDtos.add(carDto);
        }
        return carDtos;
    }

    public List<CarDTO> getAllCars() throws DataAccessException {

        CarsProcessor cp = new CarsProcessor();
        List<CarData> allCars = cp.getAllCars();

        List<CarDTO> allCarDTOs = makeListOfCarDTOs(allCars);
        return allCarDTOs;
    }

    public void addACar(CarDTO carDto) throws DataAccessException {
        CarsProcessor cp = new CarsProcessor();
        CarData car = convertToCar(carDto);
        cp.addACar(car);
    }

}
