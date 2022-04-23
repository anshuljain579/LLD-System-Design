package com.home;

import com.home.commands.CommandExecutorFactory;
import com.home.service.ParkingLotService;

public class Main {
    public static void main(String[] args) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();

        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);



    }
}
