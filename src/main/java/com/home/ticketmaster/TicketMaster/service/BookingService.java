package com.home.ticketmaster.TicketMaster.service;

import com.home.ticketmaster.TicketMaster.exceptions.BadRequestException;
import com.home.ticketmaster.TicketMaster.exceptions.NotFoundException;
import com.home.ticketmaster.TicketMaster.exceptions.SeatPermanentlyUnavailableException;
import com.home.ticketmaster.TicketMaster.model.Booking;
import com.home.ticketmaster.TicketMaster.model.Seat;
import com.home.ticketmaster.TicketMaster.model.Show;
import com.home.ticketmaster.TicketMaster.provider.SeatLockProvider;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private final SeatLockProvider seatLockProvider;
    private final Map<String, Booking> showBookings;

    public BookingService(SeatLockProvider seatLockProvider){
        this.seatLockProvider = seatLockProvider;
        this.showBookings = new HashMap<>();
    }

    public void confirmBooking(@NonNull final Booking booking, @NonNull final String user){
        if(!booking.getUser().equals(user)){
            throw new BadRequestException();
        }

        for(Seat seat : booking.getSeatBooked()){
            if(!seatLockProvider.validateLock(booking.getShow(), seat, user)){
                throw new BadRequestException();
            }
        }
        booking.confirmBooking();
    }


    public List<Seat> getBookedSeats(@NonNull final Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Booking getBooking(@NonNull final String bookingId){
        if(!showBookings.containsKey(bookingId)){
            throw new NotFoundException();
        }
        return showBookings.get(bookingId);
    }

    public List<Booking> getAllBookings(@NonNull Show show){
        List<Booking> response = new ArrayList<>();
        for(Booking booking : showBookings.values()){
            if(booking.getShow().equals(show)){
                response.add(booking);
            }
        }
        return response;
    }
    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats){
        final List<Seat> bookedSeats = getBookedSeats(show);
        for(Seat seat : bookedSeats){
            if(bookedSeats.contains(seat)){
                return true;
            }
        }
        return false;
    }

    public Booking createBooking(@NonNull final String userId, @NonNull final Show show,
                                 @NonNull final List<Seat> seats) {
        if(isAnySeatAlreadyBooked(show, seats)){
            throw new SeatPermanentlyUnavailableException();
        }

        seatLockProvider.lockSeats(show, seats, userId);
        final String bookingId = UUID.randomUUID().toString();
        final Booking newBooking = new Booking(bookingId, show, userId, seats);
        showBookings.put(bookingId, newBooking);

        return newBooking;

    }
}
