package com.home.commands;

import com.home.OutputPrinter;
import com.home.model.Command;
import com.home.service.ParkingLotService;

public class ExitCommandExecutor extends CommandExecutor{
    public static String COMMAND_NAME = "exit";

    public ExitCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) {
        outputPrinter.end();
    }
}
