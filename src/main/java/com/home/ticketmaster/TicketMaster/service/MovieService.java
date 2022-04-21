package com.home.ticketmaster.TicketMaster.service;

import com.home.ticketmaster.TicketMaster.exceptions.NotFoundException;
import com.home.ticketmaster.TicketMaster.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private final Map<String, Movie> movies;

    public MovieService(){
        this.movies = new HashMap<>();
    }

    public Movie getMovie(String movieId) throws NotFoundException {
        if(!movies.containsKey(movieId)){
            throw new NotFoundException();
        }
        return movies.get(movieId);
    }

    public Movie createMovie(String movieName) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, movieName);
        movies.put(movieId, movie);
        return movie;
    }
}
