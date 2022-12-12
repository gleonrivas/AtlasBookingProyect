package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Temporada;
import com.app.atlasultimate.repository.ReservaRepository;
import com.app.atlasultimate.repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private TemporadaRepository temporadaRepository;

    public Temporada temporadaporId(Integer Hab_id) {
        Integer id_temporada = reservaRepository.IdTemporadaporIdHab(Hab_id);
        Temporada temporada = temporadaRepository.temporadaPorId(id_temporada);
        return temporada;
    }

}
