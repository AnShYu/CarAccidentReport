package ru.andrey.caraccidentreport.htttpmanager;

import java.util.Objects;

public class CarDTO {
    private String carBrand;
    private String carModel;
    private String carPlate;

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Objects.equals(carBrand, carDTO.carBrand) && Objects.equals(carModel, carDTO.carModel) && Objects.equals(carPlate, carDTO.carPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carBrand, carModel, carPlate);
    }
}
