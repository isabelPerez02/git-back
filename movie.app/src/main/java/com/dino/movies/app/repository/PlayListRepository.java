package com.dino.movies.app.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dino.movies.app.entities.PlayList;
import com.dino.movies.app.interfaces.IPlayListRepository;

@Repository
public class PlayListRepository {
    
    @Autowired
    IPlayListRepository repository;
    
    public Iterable<PlayList> getAll(){
        return repository.findAll();
    }
    
    public Optional<PlayList> findById(String id){
        Optional<PlayList> response= repository.findById(id);
        return response;
    }
    
    public Boolean existsById(String id){
        return repository.existsById(id);
    }
    
    public void deleteById(String id){
        repository.deleteById(id);
    }
    
    public PlayList save(PlayList PlayList){
        return repository.save(PlayList);
    }
}
