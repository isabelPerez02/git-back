package com.dino.movies.app.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.dino.movies.app.entities.Movie;

public interface IMovieRepository extends CrudRepository<Movie, String>{

   
}
