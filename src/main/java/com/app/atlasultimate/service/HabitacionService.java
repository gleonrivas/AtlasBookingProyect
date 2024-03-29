package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> guardarHabMultiple(Integer num, Habitacion hab) {
        List<Integer> ids = new ArrayList<>();
        Habitacion hab2 = new Habitacion();
        for (int i = 0; i < num; i++) {
            hab2 = habitacionRepository.save(new Habitacion(hab));
            ids.add(hab2.getId());
        }

        return ids;
    }

    public Habitacion obtenerHabitacionporId(Integer integer) {
        return habitacionRepository.findTopById(integer);
    }

    public Habitacion actualizarHabitacion(Habitacion hab) {
        return habitacionRepository.save(hab);
    }

    public Hotel eliminarHabitacion(Integer id) {
        habitacionRepository.deleteById(id);
        return null;
    }

    public void cambiarIdTemporada (Integer id_temporada, Integer id_hab){
        habitacionRepository.cambiarIdTemporada(id_temporada, id_hab);

    }

}
