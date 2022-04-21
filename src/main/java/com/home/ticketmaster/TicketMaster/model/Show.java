package com.home.ticketmaster.TicketMaster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Show {
    private final String id;
    private final Movie movieId;
    private final Screen screen;
    private final Date startTime;
    private final Integer durationInSec;

}
