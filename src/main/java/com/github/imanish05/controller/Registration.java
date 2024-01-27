package com.github.imanish05.controller;

import com.github.imanish05.dto.User;
import com.github.imanish05.exception.FlightReservationException;
import com.github.imanish05.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class Registration {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User newRegistraion(@RequestBody User user) {

        if (user == null) {
            throw new FlightReservationException("Invalid User details");
        }
        return registrationService.registerUser(user);
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllUser() {

        return registrationService.findAll();
    }


    @GetMapping(path = "/find/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUserId(@PathVariable(value = "id") Long id) {

        return registrationService.findById(id);
    }


}
