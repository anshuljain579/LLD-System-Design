package com.home.ticketmaster.TicketMaster.service;

import com.home.ticketmaster.TicketMaster.exceptions.NotFoundException;
import com.home.ticketmaster.TicketMaster.model.Screen;
import com.home.ticketmaster.TicketMaster.model.Seat;
import com.home.ticketmaster.TicketMaster.model.Theatre;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {

    private final Map<String, Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;


    public TheatreService(){
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
    }

    public Seat getSeat(@NonNull final String seatId) {
        if(!seats.containsKey(seatId)){
            throw new NotFoundException();
        }
        return seats.get(seatId);
    }

    public Theatre getTheatre(@NonNull final String theatreId)  {
        if(!theatres.containsKey(theatreId)){
            throw new NotFoundException();
        }
        return theatres.get(theatreId);
    }

    public Screen getScreen(@NonNull String screenId){
        if(!screens.containsKey(screenId)){
            throw new NotFoundException();
        }
        return screens.get(screenId);
    }

    public Theatre createTheatre(String theatreName) {
        String threatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(threatreId, theatreName);
        theatres.put(threatreId, theatre);
        return theatre;
    }

    public Screen createScreen(String screenName, Theatre theatre){
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId, screen);
        return screen;
    }

    public Screen createScreenInTheatre(String screenName, Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    public Seat createSeatsInScreen(@NonNull Integer rowNo, @NonNull Integer seatNo, Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        seats.put(seatId, seat);
        screen.addSeats(seat);

        return seat;
    }


}
