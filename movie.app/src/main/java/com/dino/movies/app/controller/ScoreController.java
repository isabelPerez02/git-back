package com.dino.movies.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.dto.ScoreDto;
import com.dino.movies.app.entities.Score;
import com.dino.movies.app.services.ScoreService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/score")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    ScoreService service;

    @GetMapping("")
    public Iterable<Score> get() {
        return service.get();
    }

    @GetMapping("/check/{movieId}")
    public Score check(@PathVariable("movieId") String movieId,@RequestHeader(value="authorization") String authorization) {
        return service.check(movieId,authorization);
    }

     @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto create(@RequestBody ScoreDto request, @RequestHeader(value="authorization") String authorization) {
        return service.create(request, authorization);
       
    }

   /*  Validacion de que el cliente existe
   @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto>  create(@RequestBody Score request) {
        ResponseDto responseDto = service.create(request);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto,HttpStatus.CONFLICT);

        if(responseDto.status.booleanValue()==true){
            response = new ResponseEntity<>(responseDto,HttpStatus.CREATED);
        }

        return response;
    } */




    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto update(@PathVariable("id") String id,@RequestBody Score request) {
        return service.update(request,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}