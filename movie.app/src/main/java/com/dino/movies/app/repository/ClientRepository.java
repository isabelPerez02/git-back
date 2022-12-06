package com.dino.movies.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dino.movies.app.entities.Client;
import com.dino.movies.app.interfaces.IClientRepository;

@Repository
public class ClientRepository {
    
    @Autowired
    IClientRepository repository;
    
    public Iterable<Client> getAll(){
        return repository.findAll();
    }
    
    public Optional<Client> findById(String id){
        Optional<Client> response= repository.findById(id);
        return response;
    }
    
    public Boolean existsById(String id){
        return repository.existsById(id);
    }

    public List<Client> getByEmail(String email){
        return repository.getClientsByEmail(email);
    }

    public List<Client> getByPassword(String password){
        return repository.getClientsByPassword(password);
    }
    
    public void deleteById(String id){
        repository.deleteById(id);
    }
    
    public Client save(Client client){
        return repository.save(client);
    }
}
