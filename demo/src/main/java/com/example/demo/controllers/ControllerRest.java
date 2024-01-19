package com.example.demo.controllers;


import com.example.demo.domain.modelo.Alumno;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRest {


    @GetMapping("/rest")
    public Alumno index() {

        return new Alumno("juan","a", "1234", "sdfsdf@asd" );
    }

    @PostMapping("/rest")
    public Alumno indexPost() {

        return new Alumno("juan","a", "1234", "sdfsdf@asd" );
    }
}
