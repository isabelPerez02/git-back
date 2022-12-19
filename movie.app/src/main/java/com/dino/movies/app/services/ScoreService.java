package com.dino.movies.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.dto.ScoreDto;
import com.dino.movies.app.entities.Client;
import com.dino.movies.app.entities.Movie;
import com.dino.movies.app.entities.Score;
import com.dino.movies.app.repository.ClientRepository;
import com.dino.movies.app.repository.MovieRepository;
import com.dino.movies.app.repository.ScoreRepository;





@Service
public class ScoreService {

    private final String MOVIE_NO_EXIST ="No existe la pelicula a calificar";
    private final String CLIENT_NO_EXIST ="No existe el cliente para poder calificar la pelicula";
    
    @Autowired
    ScoreRepository repository;

    @Autowired
    MovieRepository repositoryMovie;

    @Autowired
    ClientRepository repositoryClient;

    @Autowired
    ClientService clientService;


    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }

    public Score check(String movieId,String authorization) {

        Score score = new Score();
        Optional<Movie> movie = repositoryMovie.findById(movieId);
        Optional<Client> client = clientService.getByCredential(authorization);
        if(movie.isPresent() && client.isPresent()){
            List<Score> scores = repository.findByMovieAndClient(movie.get().getId(),client.get().getId());
            if(scores.size()>0){
                score = scores.get(scores.size()-1);//retorna la ultima calificacion
            }
        }

        return score;
    }

     public ResponseDto create(ScoreDto request, String authorization) {
        ResponseDto response = new ResponseDto();
        response.status = false;
        if(request.score<0 || request.score>10){
            //response.status=false;
            response.message="La calificación enviada no está dentro de los valores esperados";
        }else{
            Score score = new Score();

            Optional<Movie> movie = repositoryMovie.findById(request.movieId);
            Optional<Client> client = clientService.getByCredential(authorization);
            
            if(movie.isPresent() && client.isPresent()){
                //validacion de si ya existe la calificacion
                 //puede ser que se actualice
                score.setScore(request.score);
                score.setState("activo");
                score.setMovie(movie.get());
                score.setClient(client.get());
                repository.save(score);
                response.status=true;
                response.message="Calificación guardada correctamente";
                response.id= score.getId();
            }


        }
        return response;
    }

 /*  Mi validación, pero debo revisar lo de Score y depurar
   public ResponseDto create(Score request) {
        ResponseDto response = new ResponseDto();

        List<Client> clients = repositoryClient.getByEmail(request.getClient().getEmail());
        List<Movie> movies = repositoryMovie.getByName(request.getMovie().getName());

        if(movies.size()>0 && clients.size()>0){

            if(request.getScore().intValue()<0 || request.getScore().intValue()>10){
                response.status=false;
                response.message="La calificación enviada no está dentro de los valores esperados";

                return response;
            }else{
               
            
                if(repositoryClient.findById(request.getClient().getId()) !=null){
                    if(request.getClient().getEmail()==null){
                        request.getClient().setEmail(repositoryClient.findById(request.getClient().getId()).get().getEmail());
                    }
                    if(request.getClient().getPassword()==null){
                        request.getClient().setPassword(repositoryClient.findById(request.getClient().getId()).get().getPassword());
                    }
                    if(request.getClient().getName()==null){
                        request.getClient().setName(repositoryClient.findById(request.getClient().getId()).get().getName());
                    }
                    if(request.getClient().getLastName()==null){
                        request.getClient().setLastName(repositoryClient.findById(request.getClient().getId()).get().getLastName());
                    }
                    if(request.getClient().getBirthDate()==null){
                        request.getClient().setBirthDate(repositoryClient.findById(request.getClient().getId()).get().getBirthDate());
                    }
                    if(request.getClient().getPhone()==null){
                        request.getClient().setPhone(repositoryClient.findById(request.getClient().getId()).get().getPhone());
                    }
                }
    
                if(repositoryMovie.findById(request.getMovie().getId()) !=null){
                    if(request.getMovie().getName()==null){
                        request.getMovie().setName(repositoryMovie.findById(request.getMovie().getId()).get().getName());
                    }
                    if(request.getMovie().getSynopsis()==null){
                        request.getMovie().setSynopsis(repositoryMovie.findById(request.getMovie().getId()).get().getSynopsis());
                    }
                    if(request.getMovie().getReleaseDate()==null){
                        request.getMovie().setReleaseDate(repositoryMovie.findById(request.getMovie().getId()).get().getReleaseDate());
                    }
                    if(request.getMovie().getLink()==null){
                        request.getMovie().setLink(repositoryMovie.findById(request.getMovie().getId()).get().getLink());
                    }
                }
    
            }

            Score score = new Score();
            repository.save(request);
            response.status=true;
            response.message="Calificación guardada correctamente";
            response.id= score.getId();
           response.id= request.getId();


           
        }else if(!(movies.size()>0)){
            response.status=false;
            response.message=MOVIE_NO_EXIST;
        }else{
            response.status=false;
            response.message=CLIENT_NO_EXIST;
        }
       

        return response;
    } */

    public ResponseDto update(Score score,String scoreId) {

        ResponseDto response = new ResponseDto();
        Optional<Score> currentScore = repository.findById(scoreId);
        if (!currentScore.isEmpty()) {
            Score scoreToUpdate = new Score();
            scoreToUpdate = currentScore.get();
            scoreToUpdate.setScore(score.getScore());
            repository.save(scoreToUpdate);
            response.status=true;
            response.message="Se actualizó correctamente";
            response.id=scoreId;
        }else{
            response.status=false;
            response.message="No se logró la actualización";
        }
        return response;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
   
}
