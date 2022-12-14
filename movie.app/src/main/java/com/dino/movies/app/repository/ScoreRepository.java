package com.dino.movies.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dino.movies.app.entities.Score;
import com.dino.movies.app.interfaces.IScoreRepository;

@Repository
public class ScoreRepository {
    
    @Autowired
    IScoreRepository repository;
    
    public Iterable<Score> getAll(){
        return repository.findAll();
    }
    
    public Optional<Score> findById(String id){
        Optional<Score> response= repository.findById(id);
        return response;
    }

    public List<Score> findByMovieAndClient(String movieId,String clientId){
        List<Score> response= repository.getScoreByMovieAndClient(movieId,clientId);
        return response;
    }

    
    public Boolean existsById(String id){
        return repository.existsById(id);
    }
    
    public void deleteById(String id){
        repository.deleteById(id);
    }
    
    public Score save(Score score){
        return repository.save(score);
    }
}
