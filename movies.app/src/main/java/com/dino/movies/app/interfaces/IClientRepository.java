package com.dino.movies.app.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.dino.movies.app.entities.Client;

public interface IClientRepository extends CrudRepository<Client, String>{

   
}
