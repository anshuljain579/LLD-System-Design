package com.home.model;

public class Slot {
    private Car parkedCar;
    private Integer slotNumber;

    public Slot(final Integer slotNumber){
        this.slotNumber = slotNumber;
    }

    public Integer getSlotNumber(){
        this.slotNumber = slotNumber;
        return slotNumber;
    }

    public Car getParkedCar(){
        return parkedCar;
    }

    public boolean isSlotFree(){
        return parkedCar == null;
    }

    public void assignCar(Car car){
        this.parkedCar = car;
    }

    public void unassignedCar(){
        this.parkedCar = null;
    }
}
