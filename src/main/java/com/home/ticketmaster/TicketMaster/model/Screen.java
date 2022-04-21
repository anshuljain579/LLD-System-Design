package com.home.ticketmaster.TicketMaster.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Screen {
    private String id;
    private String name;
    private Theatre theatre;
    private List<Seat> seats;

    public Screen(@NonNull String screenId, @NonNull String name, @NonNull final Theatre theatre){
        this.id = screenId;
        this.name = name;
        this.theatre = theatre;
        this.seats = new ArrayList<>();
    }

    public void addSeats(@NonNull Seat seat){
        this.seats.add(seat);
    }

}
