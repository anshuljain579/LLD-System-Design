package com.home.commands;

import com.home.OutputPrinter;
import com.home.exception.NoFreeSlotAvailableException;
import com.home.model.Car;
import com.home.model.Command;
import com.home.model.Slot;
import com.home.service.ParkingLotService;

import java.util.List;
import java.util.Optional;

public class SlotForRegNumberCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "slot_number_for_registration_name";
    public SlotForRegNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String regNumberToFind = command.getParams().get(0);
        final Optional<Slot> foundSlot = occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getRegistrationNumber().equals(regNumberToFind))
                .findFirst();

        if(foundSlot.isPresent()){
            outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
        }else{
            outputPrinter.notFound();
        }
    }


}
