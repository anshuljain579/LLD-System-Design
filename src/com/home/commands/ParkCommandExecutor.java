package com.home.commands;

import com.home.OutputPrinter;
import com.home.exception.NoFreeSlotAvailableException;
import com.home.model.Car;
import com.home.model.Command;
import com.home.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor{

    public static final String COMMAND_NAME = "park";

    public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 2;
    }

    @Override
    public void execute(Command command) {
        final Car car = new Car(command.getParams().get(0), command.getParams().get(1));
        try{
            final Integer slot = parkingLotService.park(car);
            outputPrinter.printWithNewLine("Allocated slot Number:" + slot);
        }
        catch(NoFreeSlotAvailableException exp){
            outputPrinter.parkingLotFull();
        }
    }
}
