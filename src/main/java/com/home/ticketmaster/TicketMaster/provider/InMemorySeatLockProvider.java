package com.home.ticketmaster.TicketMaster.provider;

import com.google.common.collect.ImmutableList;
import com.home.ticketmaster.TicketMaster.exceptions.SeatTemporaryUnavailableException;
import com.home.ticketmaster.TicketMaster.model.Seat;
import com.home.ticketmaster.TicketMaster.model.SeatLock;
import com.home.ticketmaster.TicketMaster.model.Show;
import com.sun.jdi.ArrayReference;
import lombok.NonNull;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider{
    private final int lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider(@NonNull final Integer lockTimeout){
        this.lockTimeout = lockTimeout;
        this.locks = new HashMap<>();
    }
    @Override
    synchronized public void lockSeats(@NonNull final Show show, @NonNull final List<Seat> seats,
                                       @NonNull final String user) {

        for(Seat seat : seats){
            if(isSeatLocked(show, seat)){
                throw new SeatTemporaryUnavailableException();
            }
        }

        for(Seat seat : seats){
            lockSeat(show, seat, user, lockTimeout);
        }

    }

    private void lockSeat(final Show show, final Seat seat, final String user, final Integer timeoutInSeconds){
        if(!locks.containsKey(show)){
            locks.put(show, new HashMap<>());
        }
        final SeatLock lock = new SeatLock(seat, show, timeoutInSeconds, new Date(), user);
        locks.get(show).put(seat, lock);
    }

    @Override
    public void unlockedSeats(@NonNull final Show show,@NonNull final List<Seat> seats, @NonNull final String user) {
        for(Seat seat : seats){
            if(validateLock(show, seat, user)){
                unlockSeat(show, seat);
            }
        }
    }

    @Override
    public boolean validateLock(@NonNull final Show show, @NonNull final Seat seat, @NonNull final String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }


    private void unlockSeat(final Show show, final Seat seat) {
        if (!locks.containsKey(show)) {
            return;
        }
        locks.get(show).remove(seat);
    }
    @Override
    public List<Seat> getLockedSeats(@NonNull final Show show) {
        if(!locks.containsKey(show)){
            return ImmutableList.of();
        }

        final List<Seat> lockedSeats = new ArrayList<>();

        for(Seat seat : locks.get(show).keySet()){
            if(isSeatLocked(show, seat)){
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private boolean isSeatLocked(final Show show, final Seat seat){
        return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired();
    }
}
