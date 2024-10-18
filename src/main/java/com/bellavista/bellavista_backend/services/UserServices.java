package com.bellavista.bellavista_backend.services;

import com.bellavista.bellavista_backend.entities.User;
import com.bellavista.bellavista_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {


    @Autowired
    private UserRepository userRepository;


    public User save (User user){
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
