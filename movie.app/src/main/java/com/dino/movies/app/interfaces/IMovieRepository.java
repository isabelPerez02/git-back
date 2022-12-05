package com.dino.movies.app.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dino.movies.app.entities.Movie;

public interface IMovieRepository extends CrudRepository<Movie, String>{

    @Query(value="{name :?0}")
    List<Movie> getMoviesByName(String name);
   
}
