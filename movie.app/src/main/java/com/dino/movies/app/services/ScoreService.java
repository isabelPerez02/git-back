package com.dino.movies.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.entities.Score;
import com.dino.movies.app.repository.ClientRepository;
import com.dino.movies.app.repository.MovieRepository;
import com.dino.movies.app.repository.ScoreRepository;




@Service
public class ScoreService {
    
    @Autowired
    ScoreRepository repository;

    @Autowired
    MovieRepository repositoryMovie;

    @Autowired
    ClientRepository repositoryClient;


    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }

    public ResponseDto create(Score request) {
        ResponseDto response = new ResponseDto();
        if(request.getScore().intValue()<0 || request.getScore().intValue()>10){
            response.status=false;
            response.message="La calificación enviada no está dentro de los valores esperados";
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
                if(request.getMovie().getDirector()==null){
                    request.getMovie().setDirector(repositoryMovie.findById(request.getMovie().getId()).get().getDirector());
                }
                if(request.getMovie().getReleaseDate()==null){
                    request.getMovie().setReleaseDate(repositoryMovie.findById(request.getMovie().getId()).get().getReleaseDate());
                }
                if(request.getMovie().getLink()==null){
                    request.getMovie().setLink(repositoryMovie.findById(request.getMovie().getId()).get().getLink());
                }
                if(request.getMovie().getCategory()==null){
                    request.getMovie().setCategory(repositoryMovie.findById(request.getMovie().getId()).get().getCategory());
                }
            }

                repository.save(request);
                response.status=true;
                response.message="Calificación guardada correctamente";
                response.id= request.getId();
           

        }
        return response;
    }

    public Score update(Score score) {
        Score scoreToUpdate = new Score();

        Optional<Score> currentScore = repository.findById(score.getId());
        if (!currentScore.isEmpty()) {
            scoreToUpdate = score;
            if(score.getScore()==null){
                score.setScore(currentScore.get().getScore());
            }
            if(score.getState()==null){
                score.setState(currentScore.get().getState());
            }
            if(score.getClient()==null){
                score.setClient(currentScore.get().getClient());
            }
            if(score.getMovie()==null){
                score.setMovie(currentScore.get().getMovie());
            }
            scoreToUpdate=repository.save(scoreToUpdate);
        }
        return scoreToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
   
}
