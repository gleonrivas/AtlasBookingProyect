package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Temporada;
import com.app.atlasultimate.repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporadaService {
    @Autowired
    TemporadaRepository temporadaRepository;

    public void guardarTemporada(Temporada t){
        temporadaRepository.save(t);
    }
    public Temporada traerUltimaTemporada (){
        return temporadaRepository.ultimaTemporada();
    }
}
