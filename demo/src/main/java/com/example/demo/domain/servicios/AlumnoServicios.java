package com.example.demo.domain.servicios;

import com.example.demo.data.modelo.Alumno;
import com.example.demo.data.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
public class AlumnoServicios {



    public AlumnoServicios(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }


    private final AlumnoRepository alumnoRepository;



    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    public int insertAlumno(Alumno alumno) {
        return alumnoRepository.insertAlumno(alumno);
    }



}
