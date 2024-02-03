package ru.andrey.caraccidentreport.model;

import java.util.Objects;

public class Address {
    private String province;
    private String city;
    private String district;
    private String street;
    private String building;

    public Address(String province, String city, String district, String street, String building) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(province, address.province) && Objects.equals(city, address.city) && Objects.equals(district, address.district) && Objects.equals(street, address.street) && Objects.equals(building, address.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, district, street, building);
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}
