package com.bellavista.bellavista_backend.services;

import com.bellavista.bellavista_backend.entities.Ospite;
import com.bellavista.bellavista_backend.repository.OspiteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OspiteService {


    @Autowired
    private OspiteRepository ospiteRepository;


    //findAll

    public List<Ospite> findAll(){
        return ospiteRepository.findAll();
    }

    //findByID

    public Optional <Ospite> findById(UUID id){

        return ospiteRepository.findById(id);
    }

    //save

    public Ospite save (Ospite ospite){
        return ospiteRepository.save(ospite);

    }

    //delete

    public void delete (UUID id){
        ospiteRepository.deleteById(id);
    }



}
