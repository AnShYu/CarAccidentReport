package ru.andrey.caraccidentreport.model;

public class AccidentParticipantData {
    private String OSAGO;
    private int accidentID;
    private int driverID;
    private int carID;

    public String getOSAGO() {
        return OSAGO;
    }

    public void setOSAGO(String OSAGO) {
        this.OSAGO = OSAGO;
    }

    public int getAccidentID() {
        return accidentID;
    }

    public void setAccidentID(int accidentID) {
        this.accidentID = accidentID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }
}
