package com.home.commands;

import com.home.OutputPrinter;
import com.home.model.Command;
import com.home.service.ParkingLotService;
import com.home.validator.IntegerValidator;

import java.util.List;

public class LeaveCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(final Command command) {
        final List<String> params = command.getParams();
        if(params.size() != 1){
            return false;
        }
        return IntegerValidator.isInteger(params.get(0));
    }

    @Override
    public void execute(final Command command) {
        final int slotNum = Integer.parseInt(command.getParams().get(0));
        parkingLotService.makeSlotFree(slotNum);
        outputPrinter.printWithNewLine("Slot Number :" + slotNum + " is free");
    }
}
