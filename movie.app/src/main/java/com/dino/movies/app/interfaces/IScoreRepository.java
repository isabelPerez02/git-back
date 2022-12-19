package com.dino.movies.app.interfaces;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dino.movies.app.entities.Score;

public interface IScoreRepository extends CrudRepository<Score, String>{

    @Query(value= "{$and:[{'movie.id' : ?0},{'client.id':?1}]}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Score> getScoreByMovieAndClient(String movieId,String clientId);
   
}
