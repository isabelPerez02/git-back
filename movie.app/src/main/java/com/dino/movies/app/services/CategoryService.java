package com.dino.movies.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.entities.Category;
import com.dino.movies.app.repository.CategoryRepository;


@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository repository;

    public Iterable<Category> get() {
        Iterable<Category> response = repository.getAll();
        return response;
    }

    public ResponseDto create(Category request) {

        Category newCategory = repository.save(request);

        ResponseDto responseDto = new ResponseDto();
        responseDto.status=true;
        responseDto.message="Categoría creada correctamente";
        responseDto.id= newCategory.getId();
        return responseDto;

    }

 /*    public Category create(Category request) {

        return repository.save(request);

    }*/

    public Category update(Category category) {
        Category CategoryToUpdate = new Category();

        Optional<Category> currentCategory = repository.findById(category.getId());
        if (!currentCategory.isEmpty()) {            
            CategoryToUpdate = category;
            if(category.getName()==null){
                category.setName(currentCategory.get().getName());
            }
            if(category.getDescription()==null){
                category.setDescription(currentCategory.get().getDescription());
            }
            if(category.getAgeMinium()==null){
                category.setAgeMinium(currentCategory.get().getAgeMinium());
            }
            CategoryToUpdate=repository.save(CategoryToUpdate);
        }
        return CategoryToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
