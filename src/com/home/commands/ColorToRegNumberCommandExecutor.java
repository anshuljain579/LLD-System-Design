package com.home.commands;

import com.home.OutputPrinter;
import com.home.model.Command;
import com.home.model.Slot;
import com.home.service.ParkingLotService;

import java.util.List;
import java.util.stream.Collectors;

public class ColorToRegNumberCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "registration_numbers_for_cars_with_color";

    public ColorToRegNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(final Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        final List<Slot> slotsForColor = parkingLotService.getSlotForColor(command.getParams().get(0));
        if(slotsForColor.isEmpty()){
            outputPrinter.notFound();
        }
        else{
            final String result =
                    slotsForColor.stream()
                            .map(slot -> slot.getParkedCar().getRegistrationNumber())
                            .collect(Collectors.joining(", "));

            outputPrinter.printWithNewLine(result);

        }
    }
}
