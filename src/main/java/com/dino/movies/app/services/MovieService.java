package com.dino.movies.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dino.movies.app.entities.Movie;
import com.dino.movies.app.repository.MovieRepository;




@Service
public class MovieService {
    
    @Autowired
    MovieRepository repository;

    public Iterable<Movie> get() {
        Iterable<Movie> response = repository.getAll();
        return response;
    }

    public Movie create(Movie request) {

        return repository.save(request);

    }

    public Movie update(Movie movie) {
        Movie movieToUpdate = new Movie();

        Optional<Movie> currentMovie = repository.findById(movie.getId());
        if (!currentMovie.isEmpty()) {            
            movieToUpdate = movie;
            movieToUpdate=repository.save(movieToUpdate);
        }
        return movieToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
