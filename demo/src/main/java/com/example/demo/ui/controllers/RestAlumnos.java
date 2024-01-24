package com.example.demo.ui.controllers;


import com.example.demo.data.modelo.Alumno;
import com.example.demo.domain.modelo.AlumnoModelo;
import com.example.demo.domain.servicios.AlumnoServicios;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAlumnos {



    private final AlumnoServicios alumnoServicios;

    public RestAlumnos(AlumnoServicios alumnoServicios) {
        this.alumnoServicios = alumnoServicios;
    }

    @GetMapping("/rest")
    public List<Alumno> index() {

        return  alumnoServicios.findAll();
    }

    @PostMapping("/rest")
    public AlumnoModelo indexPost() {

        return new AlumnoModelo("juan","a", "1234", "sdfsdf@asd" );
    }
}
