package com.dino.movies.app.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.dino.movies.app.entities.PlayList;

public interface IPlayListRepository extends CrudRepository<PlayList, String>{

   
}
