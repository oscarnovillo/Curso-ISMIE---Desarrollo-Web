package com.example.demo.ui.controllers;

import com.example.demo.domain.modelo.VisitaDTO;
import com.example.demo.domain.servicios.VisitasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class VisitasRest {

    private final VisitasService visitasService;


    public VisitasRest(VisitasService visitasService) {
        this.visitasService = visitasService;
    }

    @GetMapping("/visitas")
    public List<VisitaDTO> getVisitas(Principal principal){
        log.info(principal.getName());

//        return null;
        return visitasService.getAllByUserName(principal.getName());
    }

}
