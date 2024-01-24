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

    public Alumno findAlumno(int id) {
        return alumnoRepository.findAlumno(id);
    }

    public int insertAlumno(Alumno alumno) {
        return alumnoRepository.insertAlumno(alumno);
    }

    public int deleteAlumno(int id) {
        return alumnoRepository.deleteAlumno(id);
    }

    public int updateAlumno(Alumno alumno) {
        return alumnoRepository.updateAlumno(alumno);
    }



}
