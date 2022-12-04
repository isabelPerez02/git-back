package com.dino.movies.app.entities;

import lombok.*;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("playList")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;
    private String name;
    private Client client;
    private List<Movie> movies;

}
