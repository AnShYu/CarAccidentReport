package ru.andrey.caraccidentreport.model;

public class DriverData {
    private String firstName;
    private String lastName;
    private String fathersName;
    private String passport;
    private String drivingLicense;
    private String OSAGONumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getOSAGONumber() {
        return OSAGONumber;
    }

    public void setOSAGONumber(String OSAGONumber) {
        this.OSAGONumber = OSAGONumber;
    }
}
