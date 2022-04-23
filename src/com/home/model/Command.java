package com.home.model;

import com.home.exception.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Model object to represent a input command
 */
public class Command {
    private static final String SPACE = " ";
    private String commandName;
    private List<String> params;

    public String getCommandName(){
        return commandName;
    }

    public List<String> getParams(){
        return params;
    }

    /**
     * Constructor, it takes the input line and parses the command line and param out of it.
     * If the command or its given params are not valid, then InvalidCommandException is thrown.
     */
    public Command(final String inputLine){
        final List<String> tokenList = Arrays.stream(inputLine.trim().split(SPACE))
                .map(String::trim)
                .filter(token -> (token.length() > 0)).collect(Collectors.toList());

        if(tokenList.size() == 0){
            throw new InvalidCommandException();
        }

        commandName = tokenList.get(0).toLowerCase();
        tokenList.remove(0);
        params = tokenList;
    }
}
