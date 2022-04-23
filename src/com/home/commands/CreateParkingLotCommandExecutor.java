package com.home.commands;

import com.home.OutputPrinter;
import com.home.mode.parking.strartegy.NaturalOrderParkingStrategy;
import com.home.model.Command;
import com.home.model.ParkingLot;
import com.home.service.ParkingLotService;
import com.home.validator.IntegerValidator;

import java.time.Instant;
import java.util.List;

public class CreateParkingLotCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(
            final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        final List<String> params = command.getParams();
        if(params.size() != 1) {
            return false;
        }
        return IntegerValidator.isInteger(params.get(0));
    }

    @Override
    public void execute(Command command) {
        final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
        final ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);

        parkingLotService.createParkingLot(parkingLot, new NaturalOrderParkingStrategy());
        outputPrinter.printWithNewLine(
                "Created a parking lot with " + parkingLot.getCapacity() + " slots"
        );
    }


}
