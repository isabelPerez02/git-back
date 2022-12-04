package com.dino.movies.app.entities;

import lombok.*;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("score")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;
    private Integer score;
    private String state;
    private Client client;
    private Movie movie;
    private String testdata;
}
