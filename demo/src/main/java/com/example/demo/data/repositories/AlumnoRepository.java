package com.example.demo.data.repositories;

import com.example.demo.data.modelo.Alumno;
import com.example.demo.domain.modelo.AlumnoModelo;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AlumnoRepository {


    private final JdbcClient jdbcClient;

    public AlumnoRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Alumno> findAll() {
        return jdbcClient.sql("select * from alumnos")
                .query(Alumno.class).list();
    }
}
