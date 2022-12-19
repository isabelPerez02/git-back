package com.dino.movies.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.entities.Movie;
import com.dino.movies.app.repository.CategoryRepository;
import com.dino.movies.app.repository.MovieRepository;




@Service
public class MovieService {

    
    private final String MOVIE_REGISTERED="La pelicula ya se encuentra registrada";
    private final String MOVIE_SUCCESS="La pelicula se registró correctamente";
    
    @Autowired
    MovieRepository repository;

    
    @Autowired
    CategoryRepository category;


    public Iterable<Movie> get() {
        Iterable<Movie> response = repository.getAll();

    
        return response;
    }

    public Optional<Movie> getById(String id) {
        Optional<Movie> response = repository.findById(id);
        return response;
    }

    public ResponseDto create(Movie request) {
        ResponseDto response = new ResponseDto();
        List<Movie> movies = repository.getByName(request.getName());
        
        if(movies.size()>0){
            response.status=false;
            response.message=MOVIE_REGISTERED;
        }else{
            repository.save(request);
            response.status=true;
            response.message=MOVIE_SUCCESS;
            response.id= request.getId();
        }

          

        return response;
    }
    public Movie update(Movie movie) {
        Movie movieToUpdate = new Movie();

        Optional<Movie> currentMovie = repository.findById(movie.getId());
        if (!currentMovie.isEmpty()) {            
            movieToUpdate = movie;
            if(movie.getName()==null){
                movie.setName(currentMovie.get().getName());
            }
            if(movie.getSynopsis()==null){
                movie.setSynopsis(currentMovie.get().getSynopsis());
            }
            if(movie.getReleaseDate()==null){
                movie.setReleaseDate(currentMovie.get().getReleaseDate());
            }
            if(movie.getQualification()==0){
                movie.setQualification(currentMovie.get().getQualification());
            }
            if(movie.getLink()==null){
                movie.setLink(currentMovie.get().getLink());
            }
            movieToUpdate=repository.save(movieToUpdate);
        }
        return movieToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
