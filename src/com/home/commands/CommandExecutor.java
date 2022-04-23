package com.home.commands;

import com.home.OutputPrinter;
import com.home.mode.parking.strartegy.ParkingStrategy;
import com.home.model.Command;
import com.home.service.ParkingLotService;

public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;
    protected OutputPrinter outputPrinter;

    public CommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter){
        this.outputPrinter = outputPrinter;
        this.parkingLotService = parkingLotService;
    }

    public abstract boolean validate(Command command);

    public abstract  void execute(Command command);
}
