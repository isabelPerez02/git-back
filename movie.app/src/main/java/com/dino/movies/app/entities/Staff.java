package com.dino.movies.app.entities;

import lombok.*;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("staff")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;
    private String name;
    private String lastName;
    private String rol;
}
