package com.dino.movies.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dino.movies.app.entities.Movie;
import com.dino.movies.app.services.MovieService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/Movie")
@CrossOrigin(origins = "*")
public class MovieController {
   
    @Autowired
    MovieService service;

    @GetMapping("/all")
    public Iterable<Movie> get() {
        return service.get();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody Movie request) {
        return service.create(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie update(@RequestBody Movie request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}
