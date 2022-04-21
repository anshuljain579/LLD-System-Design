package com.home.ticketmaster.TicketMaster.controller;

import com.home.ticketmaster.TicketMaster.exceptions.NotFoundException;
import com.home.ticketmaster.TicketMaster.model.Movie;
import com.home.ticketmaster.TicketMaster.model.Screen;
import com.home.ticketmaster.TicketMaster.model.Seat;
import com.home.ticketmaster.TicketMaster.model.Show;
import com.home.ticketmaster.TicketMaster.service.MovieService;
import com.home.ticketmaster.TicketMaster.service.SeatAvailabilityService;
import com.home.ticketmaster.TicketMaster.service.ShowService;
import com.home.ticketmaster.TicketMaster.service.TheatreService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class ShowController {
    private final TheatreService theatreService;
    private final MovieService movieService;

    private final SeatAvailabilityService seatAvailabilityService;
    private final ShowService showService;


    public String createShow(@NonNull final String movieId, @NonNull String screenId, @NonNull Date startTime, @NonNull Integer durationInSeconds) {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getId();

    }

    public List<String> getAvailableSeats(@NonNull final String showId){
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);

        return availableSeats.stream().map(Seat :: getId).collect(Collectors.toList());

    }
}
