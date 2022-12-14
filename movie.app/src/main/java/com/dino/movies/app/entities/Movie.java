package com.dino.movies.app.entities;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("movie")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;
    private String name;
    private String synopsis;
    private Date releaseDate;
    private double qualification;
    private String link;
    private String imageLink;
    private List<Category> categoryList;
    private List<Staff> staffList;
    
}
