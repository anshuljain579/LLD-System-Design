package com.home.exception;

public class ParkingLotException extends RuntimeException {
    public ParkingLotException(){

    }

    public ParkingLotException(String message){
        super(message);
    }
}
