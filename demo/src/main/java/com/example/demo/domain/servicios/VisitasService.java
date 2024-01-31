package com.example.demo.domain.servicios;

import com.example.demo.data.repositories.VisitasRepository;
import com.example.demo.domain.modelo.VisitaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitasService {


    private final VisitasRepository visitasRepository;

    public VisitasService(VisitasRepository visitasRepository) {
        this.visitasRepository = visitasRepository;
    }


    public List<VisitaDTO> getAllByUserName(String userName)
    {
        return visitasRepository.findAllByUser_Name(userName).stream().map(
                visitaEntity -> new VisitaDTO(visitaEntity.getId(),
                        visitaEntity.getFechaInicial(),
                        visitaEntity.getFechaFinal()
                )
        ).toList();
    }


}
