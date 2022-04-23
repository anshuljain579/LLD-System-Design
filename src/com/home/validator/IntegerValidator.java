package com.home.validator;

public class IntegerValidator {

    public static boolean isInteger(final String input){
        try{
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException exp){
            return false;
        }
    }
}
