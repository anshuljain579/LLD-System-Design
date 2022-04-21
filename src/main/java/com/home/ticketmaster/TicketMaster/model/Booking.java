package com.home.ticketmaster.TicketMaster.model;

import com.home.ticketmaster.TicketMaster.exceptions.InvalidStateException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Getter
public class Booking {
    private final String id;
    private final Show show;
    private final String user;
    private final List<Seat> seatBooked;

    private BookingStatus bookingStatus;


    public Booking(@NonNull String id, @NonNull Show show, @NonNull String user, @NonNull List<Seat> seatBooked) {
        this.id = id;
        this.show = show;
        this.seatBooked = seatBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.Created;
    }

    public boolean isConfirmed(){
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking(){
        if(this.bookingStatus != BookingStatus.Created){
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expiredBooking() throws InvalidStateException {
        if(this.bookingStatus != BookingStatus.Created){
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.Expired;
    }
}
