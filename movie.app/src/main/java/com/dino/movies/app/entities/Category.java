package com.dino.movies.app.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
import javax.persistence.*;


@Document("category")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;
    private String name;
    private String description;
 
}

