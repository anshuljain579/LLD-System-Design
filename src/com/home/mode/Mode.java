package com.home.mode;

import com.home.OutputPrinter;
import com.home.commands.CommandExecutor;
import com.home.commands.CommandExecutorFactory;
import com.home.exception.InvalidCommandException;
import com.home.model.Command;

import java.io.IOException;

public abstract class Mode {
    private CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;

    public Mode(final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter){
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    /**
     * Helper method to process a command. It basically uses CommandExecutor to run the given command.
     * @param command
     */

    protected  void processCommand(final Command command){
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if(commandExecutor.validate(command)){
            commandExecutor.execute(command);
        }
        else{
            throw new InvalidCommandException();
        }
    }

    /**
     * Abstract method to process the mode. Each Mode will process in its own way.
     */
    public abstract void process() throws IOException;
}
