package com.home.ticketmaster.TicketMaster.controller;

import com.home.ticketmaster.TicketMaster.exceptions.NotFoundException;
import com.home.ticketmaster.TicketMaster.model.Screen;
import com.home.ticketmaster.TicketMaster.model.Theatre;
import com.home.ticketmaster.TicketMaster.service.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;

@AllArgsConstructor
public class TheatreController {
    final private TheatreService theatreService;

    public String createTheatre(@NonNull String theatreName){
        return theatreService.createTheatre(theatreName).getId();
    }

    public String createScreenInTheatre(@NonNull String screenName, @NonNull String theatreId){
        final Theatre theatre = theatreService.getTheatre(theatreId);

        return theatreService.createScreenInTheatre(screenName, theatre).getId();
    }

    public String createSeatsInScreen(@NonNull Integer rowNo, @NonNull final String screenId, @NonNull final Integer seatNo) throws NotFoundException {
        final Screen screen = theatreService.getScreen(screenId);
        return theatreService.createSeatsInScreen(rowNo, seatNo, screen).getId();
    }

}
