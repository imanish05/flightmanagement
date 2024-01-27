package com.github.imanish05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.locks.LockSupport;


@SpringBootApplication(scanBasePackages = {"com.github.imanish05"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @GetMapping( path ="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>home(){

            return new ResponseEntity<>("Welcome to  Manish Airlines", HttpStatus.OK);
        }
}
