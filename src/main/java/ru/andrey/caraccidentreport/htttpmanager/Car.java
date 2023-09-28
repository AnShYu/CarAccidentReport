package ru.andrey.caraccidentreport.htttpmanager;

import java.util.Objects;

public class Car {
    private String carBrand;
    private String carModel;
    private String carPlate;

    public Car(String carBrand, String carType, String carPlate) {
        this.carBrand = carBrand;
        this.carModel = carType;
        this.carPlate = carPlate;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarPlate() {
        return carPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carBrand, car.carBrand) && Objects.equals(carModel, car.carModel) && Objects.equals(carPlate, car.carPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carBrand, carModel, carPlate);
    }
}
