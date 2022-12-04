package com.dino.movies.app.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.dino.movies.app.entities.Score;

public interface IScoreRepository extends CrudRepository<Score, String>{

   
}
