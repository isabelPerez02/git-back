package com.dino.movies.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dino.movies.app.dto.ReportClientDto;
import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.dto.AuthDto;
import com.dino.movies.app.dto.AuthResponseDto;
import com.dino.movies.app.entities.Client;
import com.dino.movies.app.services.AuthService;
import com.dino.movies.app.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
   
    @Autowired
    AuthService service;


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto check(@RequestBody AuthDto request) {
        return service.check(request);
    }

}
