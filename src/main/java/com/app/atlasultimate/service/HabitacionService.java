package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> obtenerHabitaciones(){
        return habitacionRepository.findAll();
    }

}
