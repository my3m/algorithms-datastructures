package com.design;

public abstract class Car  implements CarSecurity {
    protected String vehicleManufacturer;
    protected String color;
    protected String yearOfManufacture;
    protected String serialNumber;
    protected String type;

    protected void drive() {
        System.out.println("drive");
    }
}