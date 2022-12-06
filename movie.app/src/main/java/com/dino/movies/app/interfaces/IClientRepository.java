package com.dino.movies.app.interfaces;

//import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dino.movies.app.entities.Client;

public interface IClientRepository extends CrudRepository<Client, String>{

   @Query(value="{email :?0}")
   List<Client> getClientsByEmail(String email);

   @Query(value="{password :?0}")
    List<Client> getClientsByPassword(String password);

}
