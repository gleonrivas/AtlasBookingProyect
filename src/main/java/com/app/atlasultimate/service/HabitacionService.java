package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService implements HabitacionServicio {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> listarHabitacion() {

        return habitacionRepository.findAll();
    }
    public List<Habitacion> listarHabitacionbyIdHotel(Integer id) {

        return habitacionRepository.findAllById(id);
    }

    public Habitacion guardarhab(Habitacion h) {
        return habitacionRepository.save(h);
    }

    public void guardarHabMultiple(Integer num, Habitacion hab) {

        for (int i = 0; i < num; i++) {
            habitacionRepository.save(new Habitacion(hab));
        }

    }

    public Habitacion obtenerHabitacionporId(Integer integer) {
        return habitacionRepository.findTopById(integer);
    }

    public Habitacion actualizarHabitacion(Habitacion hab) {
        return habitacionRepository.save(hab);
    }

    public void eliminarHabitacion(Integer id) {
        habitacionRepository.deleteById(id);
    }

    public void cambiarIdTemporada (Integer id_temporada, Integer id_hab){
        habitacionRepository.cambiarIdTemporada(id_temporada, id_hab);

    }

}
