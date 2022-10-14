package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionServiceImp implements HabitacionServicio {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> listarHabitacion(){

        return habitacionRepository.findAll();
    }

    public Habitacion obtenerHabitacionporId (Integer id){
        return habitacionRepository.findById(id).get();
    }
    public Habitacion actualizarHabitacion(Habitacion hab){
        return habitacionRepository.save(hab);
    }
    public void eliminarHabitacion(Integer id){
        habitacionRepository.deleteById(id);
    }


}
