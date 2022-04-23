package com.home;

public class OutputPrinter {

    public void welcome(){
        printWithNewLine("Welcome to Go-jek Parking lot.");
    }

    public void end(){
        printWithNewLine("Thanks for using Go-jek Parking lot service");
    }

    public void notFound(){
        printWithNewLine("Not found!");
    }

    public void statusHeader(){
        printWithNewLine("Slot no. Registration No Color");
    }

    public void parkingLotFull(){
        printWithNewLine("Sorry, parking lot is full");
    }

    public void parkingLotEmpty(){
        printWithNewLine("Parking lot is empty");
    }

    public void invalidFile(){
        printWithNewLine("Invalid file given.");
    }
    public void printWithNewLine(final String msg){
        System.out.println(msg);
    }

}
