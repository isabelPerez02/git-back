package com.dino.movies.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dino.movies.app.dto.ResponseDto;
import com.dino.movies.app.entities.PlayList;
import com.dino.movies.app.repository.ClientRepository;
import com.dino.movies.app.repository.MovieRepository;
import com.dino.movies.app.repository.PlayListRepository;

@Service
public class PlayListService {
    
    @Autowired
    PlayListRepository repository;

    @Autowired
    MovieRepository repositoryMovie;

    @Autowired
    ClientRepository repositoryClient;


    public Iterable<PlayList> get() {
        Iterable<PlayList> response = repository.getAll();
        return response;
    }

    public ResponseDto create(PlayList request) {
        ResponseDto response = new ResponseDto();
        if(repositoryClient.findById(request.getClient().getId()) ==null ){
            response.status=false;
            response.message="El cliente no esta registrado";
        }else{
        
            if(repositoryClient.findById(request.getClient().getId()) !=null){
                if(request.getClient().getEmail()==null){
                    request.getClient().setEmail(repositoryClient.findById(request.getClient().getId()).get().getEmail());
                }
               
            }
                repository.save(request);
                response.status=true;
                response.message="Lista guardada correctamente";
                response.id= request.getId();
           

        }
        return response;
    }

    public PlayList update(PlayList playList) {
        PlayList playListToUpdate = new PlayList();

        Optional<PlayList> currentPlayList = repository.findById(playList.getId());
        if (!currentPlayList.isEmpty()) {
            playListToUpdate = playList;
            if(playList.getName()==null){
                playList.setName(currentPlayList.get().getName());
            }
            if(playList.getClient()==null){
                playList.setClient(currentPlayList.get().getClient());
            }
            if(playList.getClient()==null){
                playList.setClient(currentPlayList.get().getClient());
            }
            if(playList.getMovies()==null){
                playList.setMovies(currentPlayList.get().getMovies());
            }
            playListToUpdate=repository.save(playListToUpdate);
        }
        return playListToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
   
}
