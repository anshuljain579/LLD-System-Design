package com.home.mode;

import com.home.OutputPrinter;
import com.home.commands.CommandExecutorFactory;
import com.home.model.Command;

import java.io.*;

public class FileMode extends Mode{
    private String fileName;

    public FileMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter, final String fileName) {
        super(commandExecutorFactory, outputPrinter);
        this.fileName = fileName;
    }

    @Override
    public void process() throws IOException {
        final File file = new File(fileName);
        final BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(file));
        }
        catch(FileNotFoundException exp){
            outputPrinter.invalidFile();
            return;
        }

        String input = reader.readLine();
        while(input != null){
            final Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }
    }
}
