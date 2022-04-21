package com.home.ticketmaster.TicketMaster.provider;

import com.home.ticketmaster.TicketMaster.model.Seat;
import com.home.ticketmaster.TicketMaster.model.Show;

import java.util.Collection;
import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<Seat> seat, String user);
    void unlockedSeats(Show show, List<Seat> seat, String user);
    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
