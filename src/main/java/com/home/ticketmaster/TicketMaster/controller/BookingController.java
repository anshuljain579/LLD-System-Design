package com.home.ticketmaster.TicketMaster.controller;

import com.home.ticketmaster.TicketMaster.model.Seat;
import com.home.ticketmaster.TicketMaster.model.Show;
import com.home.ticketmaster.TicketMaster.service.BookingService;
import com.home.ticketmaster.TicketMaster.service.ShowService;
import com.home.ticketmaster.TicketMaster.service.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {
    private final ShowService showService;
    private final BookingService bookingService;
    private final TheatreService theatreService;

    public String createBooking(@NonNull final String userId, @NonNull final String showId,
                                @NonNull final List<String> seatIds){
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats).getId();
    }
}
