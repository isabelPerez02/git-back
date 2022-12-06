package com.dino.movies.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.dto.ReportClientDto;
import com.dino.movies.app.entities.Client;
import com.dino.movies.app.repository.ClientRepository;

@Service
public class ClientService {

    private final String ERROR_USER_EXISTS = "El usuario ya existe";
    private final String SUCCESS_USER_CREATED = "Usuario creado";

    @Autowired
    ClientRepository repository;

    public Iterable<Client> get() {
        Iterable<Client> response = repository.getAll();
        return response;
    }

    public ReportClientDto getReport() {
        Optional<Client> client = repository.findById("6384195e5054d55c317413f5");
        ReportClientDto reportClientDto = new ReportClientDto();
        reportClientDto.birthDate = client.get().getBirthDate();
        reportClientDto.email = client.get().getEmail();
        reportClientDto.id = client.get().getId();
        return reportClientDto;
    }

    public ResponseDto create(Client request) {
        ResponseDto response = new ResponseDto();
        List<Client> clients = repository.getByEmail(request.getEmail());
        if (clients.size() > 0) {
            response.status = false;
            response.message = ERROR_USER_EXISTS;

        } else {
            repository.save(request);
            response.status=true;
            response.message=SUCCESS_USER_CREATED;
            response.id= request.getId();
        }

        return response;

    }

    public Client update(Client client) {
        Client clientToUpdate = new Client();

        Optional<Client> currentClient = repository.findById(client.getId());
        if (!currentClient.isEmpty()) {
            clientToUpdate = client;
            if (client.getName() == null) {
                client.setName(currentClient.get().getName());
            }
            if (client.getEmail() == null) {
                client.setEmail(currentClient.get().getEmail());
            }
            if (client.getPassword() == null) {
                client.setPassword(currentClient.get().getPassword());
            }
            if (client.getLastName() == null) {
                client.setLastName(currentClient.get().getLastName());
            }
            if (client.getBirthDate() == null) {
                client.setBirthDate(currentClient.get().getBirthDate());
            }
            if (client.getPhone() == null) {
                client.setPhone(currentClient.get().getPhone());
            }
            clientToUpdate = repository.save(clientToUpdate);
        }
        return clientToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
