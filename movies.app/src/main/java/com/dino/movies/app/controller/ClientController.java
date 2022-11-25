package com.dino.movies.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dino.movies.app.entities.Client;
import com.dino.movies.app.services.ClientService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins = "*")
public class ClientController {
   
    @Autowired
    ClientService service;

    @GetMapping("/all")
    public Iterable<Client> get() {
        return service.get();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client request) {
        return service.create(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}
