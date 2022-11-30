package com.dino.movies.app.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.dino.movies.app.entities.Category;

public interface ICategoryRepository extends CrudRepository<Category, String>{

   
}
