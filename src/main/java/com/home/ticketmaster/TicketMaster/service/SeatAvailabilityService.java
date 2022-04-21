package com.home.ticketmaster.TicketMaster.service;

import com.home.ticketmaster.TicketMaster.model.Booking;
import com.home.ticketmaster.TicketMaster.model.Seat;
import com.home.ticketmaster.TicketMaster.model.Show;
import com.home.ticketmaster.TicketMaster.provider.SeatLockProvider;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SeatAvailabilityService {

    private final BookingService bookingService;
    private final SeatLockProvider seatLockProvider;

    public SeatAvailabilityService(@NonNull final BookingService bookingService, @NonNull SeatLockProvider seatLockProvider){
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;

    }
    public List<Seat> getAvailableSeats(Show show) {
        final List<Seat> allSeats = show.getScreen().getSeats();
        final List<Seat> unavailableSeats = getUnavailableSeats(show);

        final List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;

    }

    private List<Seat> getUnavailableSeats(@NonNull final Show show){
        final List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }
}
