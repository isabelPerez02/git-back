package com.dino.movies.app.services;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.ValueToken;

import com.dino.movies.app.dto.AuthDto;
import com.dino.movies.app.dto.AuthResponseDto;
import com.dino.movies.app.entities.Client;
import com.dino.movies.app.repository.ClientRepository;
@Service
public class AuthService {

    @Autowired
    ClientRepository repository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponseDto check(AuthDto request) {
       
        AuthResponseDto response = new AuthResponseDto();
        
        if(request.user != null && !request.user.equals("") && request.password != null && !request.password.equals("")){
            Optional<Client> client = checkCredential(request.user, request.password);

            if(client!=null && client.isPresent()){
                response.id = client.get().getId();
                response.name= client.get().getName()+ " "+client.get().getLastName();
                response.email = client.get().getEmail();
                response.token=getToken(request.user, request.password);
    
            }
        }
        
        return response;
    }//23

    private String getToken(String user, String pass){
        String tokenString = user+":"+pass;
        byte[] bytesEncoded = Base64.encodeBase64(tokenString.getBytes());
        return new String(bytesEncoded);
    }

    public Optional<Client> checkCredential(String user, String password) {

        Optional<Client> client = repository.findByEmail(user);
        if(!matchPass(password,client.get().getPassword())){
            return null;
        }
        return client;
    }

    private String encrypt(String pass){
        return this.passwordEncoder.encode(pass);
    }

    private Boolean matchPass(String pass,String dbPass){
        return this.passwordEncoder.matches(pass,dbPass);
    }
}


