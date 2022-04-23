package com.home.mode.parking.strartegy;

/**
 * Strategy which will be used to decide how slots will be used to park the car
 */
public interface ParkingStrategy {

    public void addSlot(Integer slotNumber);

    public void removeSlot(Integer slotNumber);

    public Integer getNextSlot();

}
