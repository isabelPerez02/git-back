package com.dino.movies.app.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dino.movies.app.entities.Client;

public interface IClientRepository extends MongoRepository<Client, String>{

   
}
