package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.repository.PensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PensionService {

    @Autowired
    private PensionRepository repositorio;

    public Pension pensionporIdHotel(Integer id){
        return repositorio.pensionPorIdHotel(id);
    }
}
