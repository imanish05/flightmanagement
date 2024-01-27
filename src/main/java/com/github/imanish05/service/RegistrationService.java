package com.github.imanish05.service;

import com.github.imanish05.dto.User;
import com.github.imanish05.exception.UserNotFoundException;
import com.github.imanish05.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registerRepo;

   public User registerUser(User user){
       return registerRepo.save(user);
    }

    public List<User> findAll(){

       return registerRepo.findAll();
    }

    public User findUserByName(String name){

        Optional<User> byName = registerRepo.findByName(name);
        return byName.isPresent()?byName.get():null;
    }

    public User findById(Long id){
        Optional<User> byId = registerRepo.findById(id);

        if(byId.isPresent())
            return byId.get();
        else
           throw  new UserNotFoundException("No Such User Exist with Id: "+id);
    }
}
