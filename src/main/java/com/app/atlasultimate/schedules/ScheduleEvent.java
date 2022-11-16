package com.app.atlasultimate.schedules;

import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ScheduleEvent implements
        ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ReservaRepository reservaRepository;


    public static int counter;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //to do: comprobar reserva de habitaciones
        List<Registro> reservas = reservaRepository.findAll();
        for (Registro r : reservas) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(r.getF_entrada(), formatter);
            if (date.equals(LocalDate.now())) {
                r.setActiva(false);
                reservaRepository.save(r);
            }
        }

    }
}
