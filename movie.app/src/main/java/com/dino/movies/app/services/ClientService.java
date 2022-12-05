package com.dino.movies.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dino.movies.app.dto.ReportClientDto;
import com.dino.movies.app.entities.Client;
import com.dino.movies.app.repository.ClientRepository;


@Service
public class ClientService {
    
    @Autowired
    ClientRepository repository;

    public Iterable<Client> get() {
        Iterable<Client> response = repository.getAll();
        return response;
    }

    public ReportClientDto getReport() {
        Optional<Client> client = repository.findById("6384195e5054d55c317413f5");
        ReportClientDto reportClientDto= new ReportClientDto();
        reportClientDto.birthDate=client.get().getBirthDate();
        reportClientDto.email=client.get().getEmail();
        reportClientDto.id=client.get().getId();
        return reportClientDto;
    }

    public Client create(Client request) {

        return repository.save(request);

    }

    public Client update(Client client) {
        Client clientToUpdate = new Client();

        Optional<Client> currentClient = repository.findById(client.getId());
        if (!currentClient.isEmpty()) {            
            clientToUpdate = client;
            if(client.getName()==null){
                client.setName(currentClient.get().getName());
            }
            if(client.getEmail()==null){
                client.setEmail(currentClient.get().getEmail());
            }
            if(client.getPassword()==null){
                client.setPassword(currentClient.get().getPassword());
            }
            if(client.getLastName()==null){
                client.setLastName(currentClient.get().getLastName());
            }
            if(client.getBirthDate()==null){
                client.setBirthDate(currentClient.get().getBirthDate());
            }
            if(client.getPhone()==null){
                client.setPhone(currentClient.get().getPhone());
            }
            clientToUpdate=repository.save(clientToUpdate);
        }
        return clientToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
