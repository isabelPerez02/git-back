package com.dino.movies.app.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
import javax.persistence.*;


@Document("client")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String birthDate;
    private String phone;
}

