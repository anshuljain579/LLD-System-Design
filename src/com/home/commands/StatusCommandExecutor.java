package com.home.commands;

import com.home.OutputPrinter;
import com.home.model.Command;
import com.home.service.ParkingLotService;

public class StatusCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "";

    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return false;
    }

    @Override
    public void execute(Command command) {

    }
}
