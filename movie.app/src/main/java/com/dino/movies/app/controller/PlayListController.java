package com.dino.movies.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.entities.PlayList;
import com.dino.movies.app.services.PlayListService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/playList")
@CrossOrigin(origins = "*")
public class PlayListController {

    @Autowired
    PlayListService service;

    @GetMapping("")
    public Iterable<PlayList> get() {
        return service.get();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto create(@RequestBody PlayList request) {
       /*  if(request.getPlayList().intValue()>4){
            System.out.println("mayor a 4");
        }*/
        return service.create(request);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PlayList update(@RequestBody PlayList request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}