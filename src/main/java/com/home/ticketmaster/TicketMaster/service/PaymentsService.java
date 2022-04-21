package com.home.ticketmaster.TicketMaster.service;

import com.home.ticketmaster.TicketMaster.exceptions.BadRequestException;
import com.home.ticketmaster.TicketMaster.model.Booking;
import com.home.ticketmaster.TicketMaster.provider.SeatLockProvider;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class PaymentsService {

    Map<Booking, Integer> bookingFailure;
    private final Integer allowedRetries;
    private final SeatLockProvider seatLockProvider;
    public PaymentsService(@NonNull final Integer allowedRetries, SeatLockProvider seatLockProvider){
        this.allowedRetries = allowedRetries;
        this.bookingFailure = new HashMap<>();
        this.seatLockProvider = seatLockProvider;
    }

    public void processPaymentFailed(@NonNull final Booking booking, @NonNull final String user){
        if(!booking.getUser().equals(user)){
            throw new BadRequestException();
        }

        if(!bookingFailure.containsKey(booking)){
            bookingFailure.put(booking, 0);
        }

        final Integer currentFailureCounts = bookingFailure.get(booking);
        final Integer newFailureCount = currentFailureCounts + 1;
        bookingFailure.put(booking, newFailureCount);

        if(newFailureCount > allowedRetries){
            seatLockProvider.unlockedSeats(booking.getShow(), booking.getSeatBooked(), booking.getUser());
        }
    }
}
