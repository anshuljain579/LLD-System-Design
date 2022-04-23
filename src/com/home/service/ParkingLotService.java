package com.home.service;

import com.home.exception.ParkingLotException;
import com.home.mode.parking.strartegy.ParkingStrategy;
import com.home.model.Car;
import com.home.model.ParkingLot;
import com.home.model.Slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for enable the functioning of a parking lot. This will have all the business logic of
 * how the parking service will operate.
 */
public class ParkingLotService {
    private ParkingLot parkingLot;

    private ParkingStrategy parkingStrategy;

    /**
     * Allots a parking lot into the parking service. Throws ParkingLotException if there is
     * already a parking lot allowed to the service previously
     *
     */

    public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy){
        if(this.parkingLot != null){
            throw new ParkingLotException("Parking lot already exists");
        }

        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        for(int i=1; i <= parkingLot.getCapacity(); i++){
            parkingStrategy.addSlot(i);
        }
    }

    /**
     * Parks a car into the parking lot. ParkingStrategy is used to decide the slot number and then
     * the car is parked into the ParkingLot into the slot number
     */

    public Integer park(Car car){
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car, nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    public void makeSlotFree(final Integer slotNumber){
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }

    public List<Slot> getOccupiedSlots(){
        validateParkingLotExists();
        final List<Slot> occupiedSlots = new ArrayList<>();
        final Map<Integer, Slot> allSlots = parkingLot.getSlots();

        for(int i=1; i <= parkingLot.getCapacity(); i++){
            final Slot slot = allSlots.get(i);
            if(!slot.isSlotFree()){
                occupiedSlots.add(slot);
            }
        }
        return occupiedSlots;
    }

    private void validateParkingLotExists(){
        if(parkingLot == null)
            throw new ParkingLotException("Parking lot does not exists to park");
    }

    public List<Slot> getSlotForColor(final String color){
        final List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getColor().equals(color))
                .collect(Collectors.toList());
    }
}
